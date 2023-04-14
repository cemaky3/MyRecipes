package com.example.myapplication11.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.R
import com.example.myapplication11.RecyclerView.RecipeAdapter
import com.example.myapplication11.databinding.FragmentFavoriteBinding
import com.example.myapplication11.databinding.FragmentSearchBinding

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
        val recipes = FakeFoodRepository().rec
        recyclerView.adapter = RecipeAdapter(recipes)
        return binding.root
    }
}