package com.example.myapplication11.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.example.myapplication11.Retrofit.Builder
import com.example.myapplication11.Retrofit.RecipeApiResponse
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.SearchFragmentRecyclerView.SearchFragmentAdapter
import com.example.myapplication11.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val repository = FakeFoodRepository()
    private val localRecipes = FakeFoodRepository.getRecipes()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("search","reAttached")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        val recyclerView = binding.searchFragmentRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (this.arguments == null) {

            val dataModels = listRecipeToDataModelRecipe(localRecipes)
            val itemList = mutableListOf<DataModel>()

            itemList.apply {
                add(DataModel.Searchbar(getString(R.string.main_title_text)))
                add(DataModel.Header(getString(R.string.pop_recipes),getString(R.string.more_btn)))
                add(DataModel.Header("Рекомендуем",getString(R.string.more_btn)))
                addAll(dataModels)
            }

            val adapter = SearchFragmentAdapter(itemList,childFragmentManager)
            recyclerView.adapter = adapter

            val call = Builder.RecipeApiClient.apiClient.getRecipes()

            call.enqueue(object: Callback<List<RecipeApiResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeApiResponse>>,
                    response: Response<List<RecipeApiResponse>>
                ) {
                    val response = response.body()!![0].recipes
                    Log.d("Retrofit","Success")

                    val respData = listRecipeToDataModelRecipeResponse(listRecipeResponseToRecipe(response))

                    itemList.addAll(2,respData)
                    adapter.notifyDataSetChanged()
                    Log.d("Retrofit","notify")
                }

                override fun onFailure(call: Call<List<RecipeApiResponse>>, t: Throwable) {
                    Log.e("Retrofit",toString())
                }
            }
            )
        } else {
            val args = this.arguments
            val filter = args?.get("FilterData")
            var recipesList = localRecipes
            if (filter != null && filter is BottomSheetDialog.FilterDataHolder) {
                recipesList = filterListOfRecipes(filter,localRecipes,repository)
            }
            val dataModels = listRecipeToDataModelRecipe(recipesList)
            val itemList = mutableListOf<DataModel>()

            itemList.addAll(dataModels)

            recyclerView.adapter = SearchFragmentAdapter(itemList,childFragmentManager)
        }



        return binding.root
    }
    private fun listRecipeToDataModelRecipe(list: List<Recipe>): List<DataModel.Recipe> {
       return list.map { recipe ->
            DataModel.Recipe(
                name = recipe.name,
                mealTime = recipe.Mealtime,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating
            )
        }
    }
    private fun listRecipeToDataModelRecipeResponse(list: List<Recipe>): List<DataModel.RecipeResponse> {
       return list.map { recipe ->
            DataModel.RecipeResponse(
                name = recipe.name,
                mealTime = recipe.Mealtime,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating,
                url = recipe.url
            )
        }
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
    private fun filterListOfRecipes(
        filter: BottomSheetDialog.FilterDataHolder,
        list: List<Recipe>,
        rep: FakeFoodRepository
    ): List<Recipe> {
        var result = list

        filter.mealTime?.let { result = rep.categoryFilter(list,it) }
        filter.dishes?.let { result = rep.dishFilter(result,it) }
        filter.calories?.let { result = rep.setCaloriesRange(result, calHigh = it) }
        filter.portions?.let { result = rep.setPortionsRange(result,max = it) }
        filter.time?.let { result = rep.setTimeRange(result,max = it) }

        return result
    }

}