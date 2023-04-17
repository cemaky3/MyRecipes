package com.example.myapplication11.SearchFragmentRecyclerView

import com.example.myapplication11.Meals

sealed class DataModel {
    data class Recipe (val name: String,
                       val category: Meals,
                       val time: Int,
                       val portions: Int,
                       val calories: Int,
                       val favorite: Boolean = false,
                       val rating: Int = 0
            ) : DataModel()
    data class Header(val header_text: String,
                      val more_btn_text: String
    ) : DataModel()
    data class Searchbar(val header_text: String) : DataModel()
}