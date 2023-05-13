package com.example.myapplication11.SearchFragmentRecyclerView

import com.example.myapplication11.Dishes
import com.example.myapplication11.Meals

sealed class DataModel {
    data class Recipe (val id: Int,
                       val name: String,
                       val mealTime: Meals,
                       val time: Int,
                       val portions: Int,
                       val calories: Int,
                       val favorite: Boolean = false,
                       val rating: Double = 0.0,
                       val dishes: Dishes
            ) : DataModel()
    data class RecipeResponse (val name: String,
                       val mealTime: Meals,
                       val time: Int,
                       val portions: Int,
                       val calories: Int,
                       val favorite: Boolean = false,
                       val rating: Double = 0.0,
                       val url: String
            ) : DataModel()
    data class Header(val header_text: String,
                      val more_btn_text: String
    ) : DataModel()
    data class Searchbar(val header_text: String) : DataModel()
}