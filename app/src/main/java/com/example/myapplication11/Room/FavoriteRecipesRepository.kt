package com.example.myapplication11.Room

import androidx.lifecycle.LiveData

interface FavoriteRecipesRepository {
    fun getFavoriteRecipes(): LiveData<List<RecipeEntity>>
    fun addFavoriteRecipe(recipe: RecipeEntity)
    fun insertOrUpdate(recipe: RecipeEntity)
    fun removeFavoriteRecipe(recipe: RecipeEntity)
    fun updateFavoriteRecipe(recipe: RecipeEntity)
}
