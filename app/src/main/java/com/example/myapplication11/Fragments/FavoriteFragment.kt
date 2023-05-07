package com.example.myapplication11.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.Dishes
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.FavoriteFragmentRecyclerView.RecipeAdapter
import com.example.myapplication11.Recipe
import com.example.myapplication11.Retrofit.Builder
import com.example.myapplication11.Retrofit.RecipeApiInterface
import com.example.myapplication11.Retrofit.RecipeApiResponse
import com.example.myapplication11.Room.LocalRecipeStorageDAO
import com.example.myapplication11.Room.RecipeDB
import com.example.myapplication11.Room.RecipeEntity
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.databinding.FragmentFavoriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    private lateinit var recipeDB: RecipeDB
    private lateinit var dao: LocalRecipeStorageDAO
    private lateinit var recipeList: MutableList<RecipeEntity>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("fragment","reAttachedFav")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeDB = RecipeDB.getInstance(requireContext())!!
        dao = recipeDB.Recipes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.favoriteRecipesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeList = mutableListOf()

        val adapter = RecipeAdapter(recipeList)
        recyclerView.adapter = adapter


        dao.addAll().observe(viewLifecycleOwner, Observer {
            recipeList.clear()
            recipeList.addAll(it)
            adapter.notifyDataSetChanged()
            Log.d("Live","Success")})

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