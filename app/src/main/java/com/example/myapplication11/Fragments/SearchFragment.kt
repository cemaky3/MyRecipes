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
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.SearchFragmentRecyclerView.SearchFragmentAdapter
import com.example.myapplication11.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val repository = FakeFoodRepository()
    private val recipes = repository.getRecipes()

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

            val dataModels = listRecipeToDataModelRecipe(recipes)
            val itemList = mutableListOf<DataModel>()

            itemList.add(DataModel.Searchbar(getString(R.string.main_title_text)))
            itemList.add(DataModel.Header(getString(R.string.pop_recipes),getString(R.string.more_btn)))
            itemList.add(DataModel.Header("Рекомендуем",getString(R.string.more_btn)))
            itemList.addAll(dataModels)

            recyclerView.adapter = SearchFragmentAdapter(itemList,childFragmentManager)
        } else {
            val args = this.arguments
            val filter = args?.get("FilterData")
            var recipesList = recipes
            if (filter != null && filter is BottomSheetDialog.FilterDataHolder) {
                recipesList = filterListOfRecipes(filter,recipes,repository)
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
                category = recipe.category,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating
            )
        }
    }
    private fun filterListOfRecipes(
        filter: BottomSheetDialog.FilterDataHolder,
        list: List<Recipe>,
        rep: FakeFoodRepository
    ): List<Recipe> {
        var result = list

        filter.mealTime?.let { mealTime ->
             result = rep.categoryFilter(list,mealTime) }
        filter.dishes?.let { result = rep.dishFilter(result,it) }
        filter.calories?.let { result = rep.setCaloriesRange(result, calHigh = it) }
        filter.portions?.let { result = rep.setPortionsRange(result,max = it) }
        filter.time?.let { result = rep.setTimeRange(result,max = it) }

        return result
    }

}