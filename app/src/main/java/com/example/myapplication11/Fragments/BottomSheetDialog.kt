package com.example.myapplication11.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.myapplication11.Dishes
import com.example.myapplication11.Meals
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.example.myapplication11.databinding.FragmentBottomSheetDialogBinding
import com.example.myapplication11.databinding.FragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.Serializable

class BottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding?  = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetDialogBinding.inflate(inflater,container,false)

        val result = FilterDataHolder(
            calories = binding.seekBarCalories.progress,
            portions = binding.seekBarPortions.progress,
            time = binding.seekBarCookingtime.progress
        )

        binding.mealtimeChipgroupFilter.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
            R.id.chip_breakfast -> result.mealTime = Meals.Breakfast
            R.id.chip_lunch -> result.mealTime = Meals.Lunch
            R.id.chip_dinner -> result.mealTime = Meals.Dinner
           }
        }
        binding.dishesChipgroupFilter.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
            R.id.chip_soup -> result.dishes = Dishes.Soup
            R.id.chip_salad -> result.dishes = Dishes.Salad
            R.id.chip_drinks -> result.dishes = Dishes.Drinks
            R.id.chip_main_dish-> result.dishes = Dishes.MainDish
            }
        }

        binding.seekBarCalories.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBarCaloriesNumber.text = seekBar?.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                result.calories = seekBar?.progress
            }
        })
        binding.seekBarPortions.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBarPortionsNumber.text = seekBar?.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                result.portions = seekBar?.progress
            }
        })
        binding.seekBarCookingtime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBarCookingtimeNumber.text = seekBar?.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                result.time = seekBar?.progress
            }
        })

        binding.applyBtn.setOnClickListener {

            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putSerializable("FilterData", result)
            fragment.arguments = bundle

//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_home_container, fragment)
//                .commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): BottomSheetDialog {
            return BottomSheetDialog()
        }
    }
    class FilterDataHolder(
        var mealTime: Meals? = null,
        var dishes: Dishes? = null,
        var calories: Int? = null,
        var portions: Int? = null,
        var time: Int? = null
    ): Serializable
}