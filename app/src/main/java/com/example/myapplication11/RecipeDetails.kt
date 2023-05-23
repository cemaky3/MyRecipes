package com.example.myapplication11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication11.databinding.ActivityRecipeDetailsBinding

class RecipeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.extras?.let { it.get("RecipeID").toString().toInt() } ?: 1

        val recipe = FakeFoodRepository().getRecipeById(id, FakeFoodRepository.getRecipes())
        binding.apply {
            mealtimeTitle.text = recipe.Mealtime.toString()
            recipeName.text = recipe.name
            ratingBar.rating = recipe.rating.toFloat()
            cookingTime.text = "${recipe.time} мин"
            calories.text = recipe.calories.toString()
        }
    }
}
