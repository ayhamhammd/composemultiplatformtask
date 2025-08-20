package org.example.cmp.presentation.screens.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import composemultiplatformtask.composeapp.generated.resources.Res
import composemultiplatformtask.composeapp.generated.resources.meals_title
import composemultiplatformtask.composeapp.generated.resources.no_meals_found
import composemultiplatformtask.composeapp.generated.resources.tab_beef
import composemultiplatformtask.composeapp.generated.resources.tab_seafood
import org.example.cmp.domain.entities.Meal
import org.example.cmp.presentation.components.MealCard
import org.example.cmp.presentation.components.UiStateWrapper
import org.example.cmp.presentation.states.UiState
import org.jetbrains.compose.resources.stringResource
import org.example.cmp.utils.UiConstants

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MealsListScreen(
    seafoodMeals: UiState<List<Meal>>,
    beefMeals: UiState<List<Meal>>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onRetry: () -> Unit,
    onMealClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = selectedTab,
        pageCount = { 2 }
    )
    
    // Sync pager state with selected tab
    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }
    
    // Sync selected tab with pager state
    LaunchedEffect(pagerState.currentPage) {
        onTabSelected(pagerState.currentPage)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.meals_title),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Tab(
                    text = { Text(stringResource(Res.string.tab_seafood)) },
                    selected = pagerState.currentPage == 0,
                    onClick = { onTabSelected(0) }
                )
                Tab(
                    text = { Text(stringResource(Res.string.tab_beef)) },
                    selected = pagerState.currentPage == 1,
                    onClick = { onTabSelected(1) }
                )
            }
            
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val currentState = if (page == 0) seafoodMeals else beefMeals
                
                MealsPageContent(
                    state = currentState,
                    onRetry = onRetry,
                    onMealClick = onMealClick
                )
            }
        }
    }
}

@Composable
private fun MealsPageContent(
    state: UiState<List<Meal>>,
    onRetry: () -> Unit,
    onMealClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UiStateWrapper(
        state = state,
        onRetry = onRetry,
        modifier = modifier
    ) { meals ->
        if (meals.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(Res.string.no_meals_found),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(UiConstants.GRID_COLUMNS),
                modifier = Modifier.fillMaxSize()
            ) {
                items(meals) { meal ->
                    MealCard(
                        meal = meal,
                        onMealClick = onMealClick,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
} 