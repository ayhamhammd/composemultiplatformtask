package org.example.cmp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.cmp.presentation.screens.details.MealDetailScreen
import org.example.cmp.presentation.screens.details.MealDetailViewModel
import org.example.cmp.presentation.screens.list.MealsListScreen
import org.example.cmp.presentation.screens.list.MealsListViewModel
import org.example.cmp.utils.NavigationConstants
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class Screen(val route: String) {
    data object MealsList : Screen(NavigationConstants.MEALS_LIST_ROUTE)
    data object MealDetail : Screen(NavigationConstants.MEAL_DETAIL_ROUTE) {
        fun createRoute(mealId: String) = NavigationConstants.createMealDetailRoute(mealId)
    }
}

@Composable
fun MealsNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MealsList.route
    ) {
        composable(Screen.MealsList.route) {
            val viewModel: MealsListViewModel = koinViewModel()
            val seafoodMeals by viewModel.seafoodMeals.collectAsState()
            val beefMeals by viewModel.beefMeals.collectAsState()
            val selectedTab by viewModel.selectedTab.collectAsState()
            
            MealsListScreen(
                seafoodMeals = seafoodMeals,
                beefMeals = beefMeals,
                selectedTab = selectedTab,
                onTabSelected = viewModel::onTabSelected,
                onRetry = viewModel::retry,
                onMealClick = { mealId ->
                    navController.navigate(Screen.MealDetail.createRoute(mealId))
                }
            )
        }
        
        composable(
            route = Screen.MealDetail.route,
            arguments = listOf(navArgument(NavigationConstants.MEAL_ID_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.savedStateHandle.get<String>(NavigationConstants.MEAL_ID_ARG) ?: ""
            val viewModel: MealDetailViewModel = koinViewModel { parametersOf(mealId) }
            val mealDetail by viewModel.mealDetail.collectAsState()
            
            MealDetailScreen(
                mealDetail = mealDetail,
                onRetry = viewModel::retry,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
