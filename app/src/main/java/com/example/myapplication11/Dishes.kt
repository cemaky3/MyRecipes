package com.example.myapplication11

import com.google.gson.annotations.SerializedName

enum class Dishes {
    Starter,
    Soup,
    Salad,
    @SerializedName("firstdish")
    FirstDish,
    @SerializedName("maindish")
    MainDish,
    Snack,
    Dessert,
    Sides,
    Drinks,
    Sauces
}