package org.example.cmp.data.repositories

import org.example.cmp.data.mappers.toDomain
import org.example.cmp.data.remote.MealApiService
import org.example.cmp.domain.entities.Meal
import org.example.cmp.domain.entities.MealDetail
import org.example.cmp.domain.repositories.MealRepository

class MealRepositoryImpl(
    private val apiService: MealApiService
) : MealRepository {
    
    override suspend fun getMealsByCategory(category: String): Result<List<Meal>> {
        return try {
            val result = apiService.getMealsByCategory(category)
            result.fold(
                onSuccess = { response ->
                    val meals = response.meals
                        .filter { it.name?.isNotBlank() == true } // Filter out meals with no name
                        .map { it.toDomain() }
                    Result.success(meals)
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getMealDetail(id: String): Result<MealDetail> {
        return try {
            val result = apiService.getMealDetail(id)
            result.fold(
                onSuccess = { response ->
                    response.meals.firstOrNull()?.let { mealDetailDto ->
                        Result.success(mealDetailDto.toDomain())
                    } ?: Result.failure(Exception("Meal detail not found"))
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 