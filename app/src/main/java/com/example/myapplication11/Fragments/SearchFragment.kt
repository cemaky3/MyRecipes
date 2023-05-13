package com.example.myapplication11.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.SearchFragmentRecyclerView.OnCheckedChangeListener
import com.example.myapplication11.SearchFragmentRecyclerView.SearchFragmentAdapter
import com.example.myapplication11.databinding.FragmentSearchBinding


class SearchFragment : Fragment(),OnCheckedChangeListener {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val repository = FakeFoodRepository()
    private val localRecipes = FakeFoodRepository.getRecipes()
    private lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.searchFragmentRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)


        if (this.arguments == null) {
            val itemList = mutableListOf<DataModel>()
            itemList.apply {
                add(DataModel.Searchbar(getString(R.string.main_title_text)))
                add(DataModel.Header(getString(R.string.pop_recipes),getString(R.string.more_btn)))
                add(DataModel.Header(getString(R.string.header_recommended),getString(R.string.more_btn)))
            }
            val adapter = SearchFragmentAdapter(itemList,childFragmentManager,this)
            recyclerView.adapter = adapter

            viewModel.localRecipesLD.observe(viewLifecycleOwner, Observer {
                itemList.addAll(it)
                adapter.notifyDataSetChanged()
            })
            viewModel.apiResponseListLD.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    itemList.addAll(2,it)
                    adapter.notifyDataSetChanged()
                    Log.d("Retrofit","adapter")
                }
            })
            viewModel.errorResponseLD.observe(viewLifecycleOwner,Observer{
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
            viewModel.filteredRecipeListLD.observe(viewLifecycleOwner,Observer{
                itemList.clear()
                itemList.add(DataModel.Searchbar(getString(R.string.main_title_text)))
                itemList.addAll(it)
                adapter.notifyDataSetChanged()
            })

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
            val adapter = SearchFragmentAdapter(itemList,childFragmentManager,this)
            recyclerView.adapter = adapter
        }

    }
    private fun listRecipeToDataModelRecipe(list: List<Recipe>): List<DataModel.Recipe> {
       return list.map { recipe ->
            DataModel.Recipe(
                id = recipe.id,
                name = recipe.name,
                mealTime = recipe.Mealtime,
                time = recipe.time,
                portions = recipe.portions,
                calories = recipe.calories,
                favorite = recipe.favorite,
                rating = recipe.rating,
                dishes = recipe.Dish
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

    override fun onCheckedChanged(position: Int, isChecked: Boolean) {
        val adapter = binding.searchFragmentRecyclerView.adapter as SearchFragmentAdapter
        val item = adapter.getItem(position) as DataModel.Recipe
        viewModel.onCheckedChanged(isChecked,item)
    }
}