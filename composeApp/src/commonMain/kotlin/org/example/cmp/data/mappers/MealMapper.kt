package org.example.cmp.data.mappers

import org.example.cmp.data.models.MealDetailDto
import org.example.cmp.data.models.MealDto
import org.example.cmp.domain.entities.Ingredient
import org.example.cmp.domain.entities.Meal
import org.example.cmp.domain.entities.MealDetail
import org.example.cmp.utils.orDefault

fun MealDto.toDomain(): Meal {
    return Meal(
        id = id,
        name = name.orDefault("Unknown Meal"),
        thumbnail = thumbnail.orDefault(""),
        category = category
    )
}

fun MealDetailDto.toDomain(): MealDetail {
    return MealDetail(
        id = id,
        name = name.orDefault("Unknown Meal"),
        thumbnail = thumbnail.orDefault(""),
        area = area,
        category = category,
        instructions = instructions,
        ingredients = extractIngredients()
    )
}

private fun MealDetailDto.extractIngredients(): List<Ingredient> {
    val ingredients = mutableListOf<Ingredient>()
    
    val ingredientMeasurePairs = listOf(
        ingredient1 to measure1,
        ingredient2 to measure2,
        ingredient3 to measure3,
        ingredient4 to measure4,
        ingredient5 to measure5,
        ingredient6 to measure6,
        ingredient7 to measure7,
        ingredient8 to measure8,
        ingredient9 to measure9,
        ingredient10 to measure10,
        ingredient11 to measure11,
        ingredient12 to measure12,
        ingredient13 to measure13,
        ingredient14 to measure14,
        ingredient15 to measure15,
        ingredient16 to measure16,
        ingredient17 to measure17,
        ingredient18 to measure18,
        ingredient19 to measure19,
        ingredient20 to measure20
    )
    
    ingredientMeasurePairs.forEach { (ingredient, measure) ->
        if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
            ingredients.add(Ingredient(name = ingredient, measure = measure))
        }
    }
    
    return ingredients
} 