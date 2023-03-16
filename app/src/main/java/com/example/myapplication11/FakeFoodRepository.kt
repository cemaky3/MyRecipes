package com.example.myapplication11

class FakeFoodRepository {
    val rec = getRecipes()
    fun getRecipes():List<Recipe>{
        val frenchtoast = Recipe("Французские тосты", Meals.Breakfast,Dishes.MainDish,17,4,
            370,"370", rating = 2)
        val muffins = Recipe("Маффины", Meals.Breakfast,Dishes.Dessert,30,2,
            250,"370", rating = 1)
        val salmon = Recipe("Лосось", Meals.Dinner,Dishes.MainDish,45,3,
            315,"370", rating = 3)
        val pancakes = Recipe("Блинчики", Meals.Breakfast,Dishes.MainDish,15,2,
            200,"370", rating = 5)
        val spaghetti = Recipe("Спагетти", Meals.Lunch,Dishes.MainDish,25,2,
            500,"370", rating = 5)

        return listOf<Recipe>(frenchtoast,muffins,salmon,pancakes,spaghetti)
    }

    fun ratingSort(list: List<Recipe>,descendingOrder: Boolean = true): List<Recipe> {
       return if (descendingOrder) list.sortedByDescending { it.rating } else list.sortedBy { it.rating }
    }
    fun categoryFilter(list: List<Recipe>, category: Meals): List<Recipe> {
        return list.filter { it.category == category}
    }
    fun setCaloriesRange(list: List<Recipe>, calLow: Int, calHigh: Int): List<Recipe> {
        return list.filter { it.calories in calLow..calHigh }
    }
    fun setTimeRange(list: List<Recipe>, min: Int, max: Int): List<Recipe> {
        return list.filter { it.time in min..max }
    }

}

