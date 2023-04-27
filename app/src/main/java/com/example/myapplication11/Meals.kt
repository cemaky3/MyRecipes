package com.example.myapplication11

import com.google.gson.annotations.SerializedName

enum class Meals {
    @SerializedName("breakfast")
    Breakfast,
    @SerializedName("lunch")
    Lunch,
    @SerializedName("dinner")
    Dinner
}