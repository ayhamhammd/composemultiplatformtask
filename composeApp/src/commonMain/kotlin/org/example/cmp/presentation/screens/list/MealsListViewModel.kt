package org.example.cmp.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.cmp.domain.entities.Meal
import org.example.cmp.domain.entities.MealCategory
import org.example.cmp.domain.usecases.GetMealsByCategoryUseCase
import org.example.cmp.presentation.states.UiState

class MealsListViewModel(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {
    
    private val _seafoodMeals = MutableStateFlow<UiState<List<Meal>>>(UiState.Loading)
    val seafoodMeals: StateFlow<UiState<List<Meal>>> = _seafoodMeals.asStateFlow()
    
    private val _beefMeals = MutableStateFlow<UiState<List<Meal>>>(UiState.Loading)
    val beefMeals: StateFlow<UiState<List<Meal>>> = _beefMeals.asStateFlow()
    
    private val _selectedTab = MutableStateFlow(0) // 0 for Seafood, 1 for Beef
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()
    
    init {
        loadMeals()
    }
    
    fun onTabSelected(tabIndex: Int) {
        _selectedTab.value = tabIndex
    }
    
    fun retry() {
        loadMeals()
    }
    
    private fun loadMeals() {
        loadSeafoodMeals()
        loadBeefMeals()
    }
    
    private fun loadSeafoodMeals() {
        _seafoodMeals.value = UiState.Loading
        viewModelScope.launch {
            getMealsByCategoryUseCase(MealCategory.SEAFOOD.value)
                .fold(
                    onSuccess = { meals ->
                        _seafoodMeals.value = UiState.Success(meals)
                    },
                    onFailure = { exception ->
                        _seafoodMeals.value = UiState.Error(
                            exception.message ?: "Failed to load seafood meals"
                        )
                    }
                )
        }
    }
    
    private fun loadBeefMeals() {
        _beefMeals.value = UiState.Loading
        viewModelScope.launch {
            getMealsByCategoryUseCase(MealCategory.BEEF.value)
                .fold(
                    onSuccess = { meals ->
                        _beefMeals.value = UiState.Success(meals)
                    },
                    onFailure = { exception ->
                        _beefMeals.value = UiState.Error(
                            exception.message ?: "Failed to load beef meals"
                        )
                    }
                )
        }
    }
} 