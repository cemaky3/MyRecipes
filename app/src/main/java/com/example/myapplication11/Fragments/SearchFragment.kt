package com.example.myapplication11.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.R
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.SearchFragmentRecyclerView.SearchFragmentAdapter
import com.example.myapplication11.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        val recyclerView = binding.searchFragmentRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recipes = FakeFoodRepository().rec
        val dataModels: List<DataModel.Recipe> = recipes.map { recipe ->
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
        val itemList = mutableListOf<DataModel>()

        itemList.add(DataModel.Searchbar(getString(R.string.main_title_text)))
        itemList.add(DataModel.Header(getString(R.string.pop_recipes),getString(R.string.more_btn)))
        itemList.add(DataModel.Header("Рекомендуем",getString(R.string.more_btn)))
        itemList.addAll(dataModels)

        recyclerView.adapter = SearchFragmentAdapter(itemList,childFragmentManager)


        return binding.root
    }

}