package com.example.myapplication11.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.Dishes
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.FavoriteFragmentRecyclerView.RecipeAdapter
import com.example.myapplication11.Recipe
import com.example.myapplication11.Retrofit.Builder
import com.example.myapplication11.Retrofit.RecipeApiInterface
import com.example.myapplication11.Retrofit.RecipeApiResponse
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.databinding.FragmentFavoriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)

        val recyclerView = binding.favoriteRecipesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recipes = mutableListOf<Recipe>()
        val adapter = RecipeAdapter(recipes)
        recyclerView.adapter = adapter

        val call = Builder.RecipeApiClient.apiClient.getRecipes()

        call.enqueue(object: Callback<List<RecipeApiResponse>> {
            override fun onResponse(
                call: Call<List<RecipeApiResponse>>,
                response: Response<List<RecipeApiResponse>>
            ) {
                val response = response.body()!![0].recipes
                Log.d("Retrofit","Success")

                recipes.clear()
                recipes.addAll(listRecipeResponseToRecipe(response))
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<RecipeApiResponse>>, t: Throwable) {
                Log.e("Retrofit",toString())
            }
        }
        )

        return binding.root
    }

    private fun listRecipeResponseToRecipe(list: List<com.example.myapplication11.Retrofit.Recipe>): List<Recipe> {
        return list.map { recipe ->
            Recipe(
                name = recipe.title,
                Mealtime = recipe.mealType,
                Dish = recipe.dishType,
                time = recipe.timeToCook/60,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.isFavorite,
                rating = recipe.rating,
                id = recipe.id,
                url = recipe.imageURL,
            )
        }
    }
}