package org.example.cmp.domain.usecases

import org.example.cmp.domain.entities.Meal
import org.example.cmp.domain.repositories.MealRepository

class GetMealsByCategoryUseCase(
    private val repository: MealRepository
) {
    suspend operator fun invoke(category: String): Result<List<Meal>> {
        return repository.getMealsByCategory(category)
    }
} 