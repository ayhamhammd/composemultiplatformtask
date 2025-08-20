package org.example.cmp.domain.usecases

import org.example.cmp.domain.entities.MealDetail
import org.example.cmp.domain.repositories.MealRepository

class GetMealDetailUseCase(
    private val repository: MealRepository
) {
    suspend operator fun invoke(id: String): Result<MealDetail> {
        return repository.getMealDetail(id)
    }
} 