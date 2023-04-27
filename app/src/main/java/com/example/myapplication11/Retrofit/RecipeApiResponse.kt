package com.example.myapplication11.Retrofit

import com.example.myapplication11.Dishes
import com.example.myapplication11.Meals
import com.google.gson.annotations.SerializedName

    data class RecipeApiResponse (
        val page: Int,
        val recipes: List<Recipe>,

        @SerializedName("page_id")
        val pageID: String
    )
    data class Recipe (
        val id: Int,
        val title: String,
        val description: String,

        @SerializedName("meal_type")
        val mealType: Meals,

        @SerializedName("dish_type")
        val dishType: Dishes,

        @SerializedName("time_to_cook")
        val timeToCook: Int,

        val rating: Double,
        val calories: Int,

        @SerializedName("is_favorite")
        val isFavorite: Boolean,

        val portions: Int,
        val category: String,

        @SerializedName("image_url")
        val imageURL: String,

        val ingredients: List<Ingredient>,
        val steps: List<Step>
    )
    data class Ingredient (
        val title: String,

        @SerializedName("ingredient_alias")
        val ingredientAlias: String,

        val count: Double,
        val unit: String,

        @SerializedName("unit_code")
        val unitCode: Int,

        @SerializedName("ingredient_url")
        val ingredientURL: String
    )
    data class Step (
        @SerializedName("step_number")
        val stepNumber: Int,

        @SerializedName("step_description")
        val stepDescription: String
    )

