package com.example.myapplication11.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val mealTime: String,
    val time: Int,
    val portions: Int,
    val calories: Int,
    val favorite: Boolean = false,
    val rating: Double = 0.0
)
