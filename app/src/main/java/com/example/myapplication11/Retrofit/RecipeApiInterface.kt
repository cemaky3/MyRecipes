package com.example.myapplication11.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RecipeApiInterface {
    @GET("recipes")
    suspend fun getRecipes() : List<RecipeApiResponse>
}