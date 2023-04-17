package com.example.myapplication11.FavoriteFragmentRecyclerView

import android.view.View
import android.widget.CheckBox
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R

class RecipeViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val mealtime: TextView = v.findViewById(R.id.strawberry_card_mealtime)
    val name: TextView = v.findViewById(R.id.card_mealtime_title)
    val calories: TextView = v.findViewById(R.id.strawberry_toast_card_calories)
    val time: TextView = v.findViewById(R.id.strawberry_card_time)
    val portions: TextView = v.findViewById(R.id.strawberry_card_portions)
    val favor: CheckBox = v.findViewById(R.id.strawberry_toast_card_isfavorite_checkbox)
    val rating: RatingBar = v.findViewById(R.id.ratingBar)
}