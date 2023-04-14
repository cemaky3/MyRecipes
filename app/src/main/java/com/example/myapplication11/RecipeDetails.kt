package com.example.myapplication11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication11.databinding.ActivityRecipeDetailsBinding

class RecipeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var id = intent.extras?.get("RecipeID").toString().toInt() ?: 1
        var id = intent.extras?.let { it.get("RecipeID").toString().toInt() } ?: 1

        val recipe = FakeFoodRepository().getRecipeById(id,FakeFoodRepository().getRecipes())

        binding.mealtimeTitle.text = recipe.category.toString()
        binding.recipeName.text = recipe.name
        binding.ratingBar.rating = recipe.rating.toFloat()
        binding.cookingTime.text = "${recipe.time.toString()} мин"
        binding.calories.text = recipe.calories.toString()

    }
}