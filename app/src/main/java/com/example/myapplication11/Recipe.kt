package com.example.myapplication11

data class Recipe(
    val id: Int,
    val name: String,
    val Mealtime: Meals,
    val Dish: Dishes,
    val time: Int,
    val portions: Int,
    val calories: Int,
    val url: String,
    val favorite: Boolean = false,
    val ingredients: String? = null,
    val rating: Double = 0.0
)
