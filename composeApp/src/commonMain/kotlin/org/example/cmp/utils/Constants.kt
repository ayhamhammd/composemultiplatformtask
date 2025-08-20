package org.example.cmp.utils

object ApiConstants {
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1"
    const val FILTER_ENDPOINT = "/filter.php"
    const val LOOKUP_ENDPOINT = "/lookup.php"
}

object NavigationConstants {
    const val MEALS_LIST_ROUTE = "meals_list"
    const val MEAL_DETAIL_ROUTE = "meal_detail/{mealId}"
    const val MEAL_ID_ARG = "mealId"
    
    fun createMealDetailRoute(mealId: String) = "meal_detail/$mealId"
}

object UiConstants {
    const val GRID_COLUMNS = 2
}
