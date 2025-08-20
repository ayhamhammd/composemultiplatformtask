package org.example.cmp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.cmp.data.models.MealDetailResponse
import org.example.cmp.data.models.MealsResponse
import org.example.cmp.utils.ApiConstants

interface MealApiService {
    suspend fun getMealsByCategory(category: String): Result<MealsResponse>
    suspend fun getMealDetail(id: String): Result<MealDetailResponse>
}

class MealApiServiceImpl(
    private val httpClient: HttpClient
) : MealApiService {
    
    override suspend fun getMealsByCategory(category: String): Result<MealsResponse> {
        return try {
            val response = httpClient.get("${ApiConstants.BASE_URL}${ApiConstants.FILTER_ENDPOINT}") {
                parameter("c", category)
            }
            Result.success(response.body<MealsResponse>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getMealDetail(id: String): Result<MealDetailResponse> {
        return try {
            val response = httpClient.get("${ApiConstants.BASE_URL}${ApiConstants.LOOKUP_ENDPOINT}") {
                parameter("i", id)
            }
            Result.success(response.body<MealDetailResponse>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
