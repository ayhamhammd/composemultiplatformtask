package org.example.cmp.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.cmp.domain.entities.MealDetail
import org.example.cmp.domain.usecases.GetMealDetailUseCase
import org.example.cmp.presentation.states.UiState

class MealDetailViewModel(
    private val mealId: String,
    private val getMealDetailUseCase: GetMealDetailUseCase
) : ViewModel() {
    
    private val _mealDetail = MutableStateFlow<UiState<MealDetail>>(UiState.Loading)
    val mealDetail: StateFlow<UiState<MealDetail>> = _mealDetail.asStateFlow()
    
    init {
        loadMealDetail()
    }
    
    fun retry() {
        loadMealDetail()
    }
    
    private fun loadMealDetail() {
        _mealDetail.value = UiState.Loading
        viewModelScope.launch {
            getMealDetailUseCase(mealId)
                .fold(
                    onSuccess = { meal ->
                        _mealDetail.value = UiState.Success(meal)
                    },
                    onFailure = { exception ->
                        _mealDetail.value = UiState.Error(
                            exception.message ?: "Failed to load meal detail"
                        )
                    }
                )
        }
    }
} 