package org.example.cmp.domain.entities

data class Meal(
    val id: String,
    val name: String,
    val thumbnail: String,
    val category: String? = null
)

data class MealDetail(
    val id: String,
    val name: String,
    val thumbnail: String,
    val area: String? = null,
    val category: String? = null,
    val instructions: String? = null,
    val ingredients: List<Ingredient> = emptyList()
)

data class Ingredient(
    val name: String,
    val measure: String
)

enum class MealCategory(val value: String) {
    SEAFOOD("Seafood"),
    BEEF("Beef");
    
    companion object {
        fun fromString(value: String): MealCategory? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
} 