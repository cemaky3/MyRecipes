package com.example.myapplication11.Fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.Recipe
import com.example.myapplication11.Retrofit.Builder
import com.example.myapplication11.Room.RecipeEntity
import com.example.myapplication11.Room.RoomFavoriteRecipesRepository
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import kotlinx.coroutines.launch

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FakeFoodRepository()
    private val localRecipes = FakeFoodRepository.getRecipes()

    val favRecipesDB = RoomFavoriteRecipesRepository(application.applicationContext)
    val apiResponseListLD = MutableLiveData<List<DataModel.RecipeResponse>>()
    val errorResponseLD = MutableLiveData<String>()
    val localRecipesLD = MutableLiveData<List<DataModel.Recipe>>()
    val filteredRecipeListLD = MutableLiveData<List<DataModel.Recipe>>()

    init {
        getRecipesFromResponse()
        localRecipesLD.value = listRecipeToDataModelRecipe(localRecipes)
    }
    private fun getRecipesFromResponse() {
        viewModelScope.launch {
            try {
                val response = Builder.RecipeApiClient.apiClient.getRecipes()[0].recipes
                val respData = listRecipeToDataModelRecipeResponse(
                    listRecipeResponseToRecipe(response)
                )
                apiResponseListLD.value = respData
            } catch (e: Exception) {
                errorResponseLD.value = e.message
            }
        }
    }
    fun setFiltered(filter: BottomSheetDialog.FilterDataHolder) {
        fun filter(dataList: List<DataModel.Recipe>, filter: BottomSheetDialog.FilterDataHolder): List<DataModel.Recipe> {
            var result = dataList

            filter.mealTime?.let { mealTime ->
                result = result.filter { it.mealTime == mealTime }
            }
            filter.dishes?.let { dishes ->
                result = result.filter { it.dishes == dishes }
            }
            filter.calories?.let { calories ->
                result = result.filter { it.calories in 0..calories }
            }
            filter.portions?.let { portions ->
                result = result.filter { it.portions in 0..portions }
            }
            filter.time?.let { time ->
                result = result.filter { it.time in 0..time }
            }

            return result
        }

        val filteredRecipes = localRecipesLD.value?.let { filter(it, filter) }
        filteredRecipeListLD.postValue(filteredRecipes)
    }

    fun onCheckedChanged(isChecked: Boolean, item: DataModel.Recipe) {
        val dbEntity = RecipeEntity(
            id = item.id,
            name = item.name,
            mealTime = item.mealTime.name,
            time = item.time,
            portions = item.portions,
            calories = item.calories,
            favorite = isChecked,
            rating = item.rating
        )
        if (isChecked) {
            favRecipesDB.insertOrUpdate(dbEntity)
        } else {
            favRecipesDB.removeFavoriteRecipe(dbEntity)
        }
    }
    private fun listRecipeToDataModelRecipeResponse(list: List<Recipe>): List<DataModel.RecipeResponse> {
        return list.map { recipe ->
            DataModel.RecipeResponse(
                name = recipe.name,
                mealTime = recipe.Mealtime,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating,
                url = recipe.url
            )
        }
    }
    private fun listRecipeResponseToRecipe(list: List<com.example.myapplication11.Retrofit.Recipe>): List<Recipe> {
        return list.map { recipe ->
            Recipe(
                name = recipe.title,
                Mealtime = recipe.mealType,
                Dish = recipe.dishType,
                time = recipe.timeToCook / 60,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.isFavorite,
                rating = recipe.rating,
                id = recipe.id,
                url = recipe.imageURL
            )
        }
    }
    private fun listRecipeToDataModelRecipe(list: List<Recipe>): List<DataModel.Recipe> {
        return list.map { recipe ->
            DataModel.Recipe(
                id = recipe.id,
                name = recipe.name,
                mealTime = recipe.Mealtime,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating,
                dishes = recipe.Dish
            )
        }
    }
}
