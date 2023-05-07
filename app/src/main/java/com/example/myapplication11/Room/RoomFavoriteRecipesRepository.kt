package com.example.myapplication11.Room;

import android.content.Context;
import androidx.lifecycle.LiveData

class RoomFavoriteRecipesRepository(context: Context): FavoriteRecipesRepository {

    var localRecipeStorage: LocalRecipeStorageDAO = RecipeDB.getInstance(context)?.Recipes()!!

    override fun getFavoriteRecipes(): LiveData<List<RecipeEntity>> {
        return localRecipeStorage.addAll()
    }

    override fun addFavoriteRecipe(recipe: RecipeEntity) {
        localRecipeStorage.insert(recipe)
    }

    override fun insertOrUpdate(recipe: RecipeEntity) {
        localRecipeStorage.insertOrUpdate(recipe)
    }

    override fun removeFavoriteRecipe(recipe: RecipeEntity) {
        localRecipeStorage.delete(recipe)
    }

    override fun updateFavoriteRecipe(recipe: RecipeEntity) {
        localRecipeStorage.update(recipe)
    }

}
