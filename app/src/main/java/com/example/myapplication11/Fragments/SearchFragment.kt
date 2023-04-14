package com.example.myapplication11.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication11.FakeFoodRepository
import com.example.myapplication11.MainActivity
import com.example.myapplication11.R
import com.example.myapplication11.RecipeDetails
import com.example.myapplication11.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        binding.filterButton.setOnClickListener {

            val filterBottomSheetDialog : BottomSheetDialog = BottomSheetDialog.newInstance()
            filterBottomSheetDialog.show(childFragmentManager,"add_filter_dialog_fragment")

        }
        binding.strawberryCardView.setOnClickListener {
            val context = requireContext()
            val intent = Intent(context, RecipeDetails::class.java)
            intent.putExtra("RecipeID", 1 )
            context.startActivity(intent)
        }

        return binding.root
    }

}