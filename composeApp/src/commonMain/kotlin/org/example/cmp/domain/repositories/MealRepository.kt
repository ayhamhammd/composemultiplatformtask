package org.example.cmp.domain.repositories

import org.example.cmp.domain.entities.Meal
import org.example.cmp.domain.entities.MealDetail

interface MealRepository {
    suspend fun getMealsByCategory(category: String): Result<List<Meal>>
    suspend fun getMealDetail(id: String): Result<MealDetail>
} 