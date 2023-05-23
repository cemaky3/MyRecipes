package com.example.myapplication11.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.FavoriteFragmentRecyclerView.RecipeAdapter
import com.example.myapplication11.Room.LocalRecipeStorageDAO
import com.example.myapplication11.Room.RecipeDB
import com.example.myapplication11.Room.RecipeEntity
import com.example.myapplication11.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    private lateinit var recipeDB: RecipeDB
    private lateinit var dao: LocalRecipeStorageDAO
    private lateinit var recipeList: MutableList<RecipeEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeDB = RecipeDB.getInstance(requireContext())!!
        dao = recipeDB.Recipes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.favoriteRecipesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeList = mutableListOf()

        val adapter = RecipeAdapter(recipeList)
        recyclerView.adapter = adapter

        dao.addAll().observe(
            viewLifecycleOwner,
            Observer {
                recipeList.clear()
                recipeList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        )
    }
}
