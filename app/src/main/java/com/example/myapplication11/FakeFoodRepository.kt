package com.example.myapplication11

class FakeFoodRepository {
    val rec = getRecipes()
    fun getRecipes():List<Recipe>{
        val frenchtoast = Recipe(1,"Французские тосты", Meals.Breakfast,Dishes.MainDish,17,4,
            370,"370", rating = 2)
        val muffins = Recipe(2,"Маффины", Meals.Breakfast,Dishes.Dessert,30,2,
            250,"370", rating = 1)
        val salmon = Recipe(3,"Лосось", Meals.Dinner,Dishes.MainDish,45,3,
            315,"370", rating = 3)
        val pancakes = Recipe(4,"Блинчики", Meals.Breakfast,Dishes.MainDish,15,2,
            200,"370", rating = 5)
        val spaghetti = Recipe(5,"Спагетти", Meals.Lunch,Dishes.MainDish,25,2,
            500,"370", rating = 5)

        return listOf<Recipe>(frenchtoast,muffins,salmon,pancakes,spaghetti)
    }
    fun getRecipeById(id: Int,listOfRecipes: List<Recipe>): Recipe {
        return listOfRecipes.first { it.id == id }
    }

    fun ratingSort(list: List<Recipe>,descendingOrder: Boolean = true): List<Recipe> {
       return if (descendingOrder) list.sortedByDescending { it.rating } else list.sortedBy { it.rating }
    }
    fun categoryFilter(list: List<Recipe>, category: Meals): List<Recipe> {
        return list.filter { it.category == category}
    }
    fun dishFilter(list: List<Recipe>, dish: Dishes): List<Recipe> {
        return list.filter { it.Dish == dish}
    }
    fun setCaloriesRange(list: List<Recipe>, calLow: Int = 0, calHigh: Int): List<Recipe> {
        return list.filter { it.calories in calLow..calHigh }
    }
    fun setPortionsRange(list: List<Recipe>, min: Int = 0, max: Int): List<Recipe> {
        return list.filter { it.portions in min..max }
    }
    fun setTimeRange(list: List<Recipe>, min: Int = 0, max: Int): List<Recipe> {
        return list.filter { it.time in min..max }
    }

}

