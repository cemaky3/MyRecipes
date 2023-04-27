package com.example.myapplication11.FavoriteFragmentRecyclerView

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(private val recipes: List<Recipe>
): RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_recipe_item,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = recipes[position]
        holder.mealtime.text = currentItem.Mealtime.toString()
        holder.name.text = currentItem.name
        holder.calories.text = currentItem.calories.toString() + " калорий"
        holder.time.text = currentItem.time.toString() + " мин"
        holder.portions.text = currentItem.portions.toString()
        holder.favor.isChecked = currentItem.favorite
        holder.rating.rating = currentItem.rating.toFloat()

        Picasso.get()
            .load(currentItem.url)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_recipe_image_default)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}