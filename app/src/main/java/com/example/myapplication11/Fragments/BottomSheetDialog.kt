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
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication11.Dishes
import com.example.myapplication11.Meals
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.databinding.FragmentBottomSheetDialogBinding
import com.example.myapplication11.databinding.FragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.Serializable

class BottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding?  = null
    private val binding get() = _binding!!
    private lateinit var stateHolder: StateHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetDialogBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireParentFragment()).get(SearchFragmentViewModel::class.java)

        val result = FilterDataHolder()
        stateHolder = StateHolder()
        binding.mealtimeChipgroupFilter.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.chip_breakfast -> {
                    result.mealTime = Meals.Breakfast
                    stateHolder.selectedMealTimeChipId = R.id.chip_breakfast
                }
                R.id.chip_lunch -> {
                    result.mealTime = Meals.Lunch
                    stateHolder.selectedMealTimeChipId = R.id.chip_lunch
                }
                R.id.chip_dinner -> {
                    result.mealTime = Meals.Dinner
                    stateHolder.selectedMealTimeChipId = R.id.chip_dinner
                }
            }
        }
        binding.dishesChipgroupFilter.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.chip_soup -> {
                    result.dishes = Dishes.Soup
                    stateHolder.selectedDishesChipId = R.id.chip_soup
                }
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
                stateHolder.selectedCalories = seekBar?.progress
            }
        })
        binding.seekBarPortions.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBarPortionsNumber.text = seekBar?.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                result.portions = seekBar?.progress
                stateHolder.selectedPortions = seekBar?.progress
            }
        })
        binding.seekBarCookingtime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBarCookingtimeNumber.text = seekBar?.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                result.time = seekBar?.progress
                stateHolder.selectedTime = seekBar?.progress
            }
        })

        binding.applyBtn.setOnClickListener {
            viewModel.setFiltered(result)
            dismiss()
        }
        binding.clearFilter.setOnClickListener {
            binding.mealtimeChipgroupFilter.clearCheck()
            binding.dishesChipgroupFilter.clearCheck()
            binding.seekBarCalories.progress = 0
            binding.seekBarCaloriesNumber.text = ""
            binding.seekBarPortions.progress = 0
            binding.seekBarPortionsNumber.text = ""
            binding.seekBarCookingtime.progress = 0
            binding.seekBarCookingtimeNumber.text = ""
            result.apply { mealTime = null; dishes = null; calories = null; portions = null; time = null }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("stateHolder", stateHolder)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            val restoredStateHolder = savedInstanceState.getSerializable("stateHolder") as StateHolder
            stateHolder = restoredStateHolder
            restoreState(restoredStateHolder)
        }
        Log.d("StateStat","Restore")
    }
    companion object {
        fun newInstance(): BottomSheetDialog {
            return BottomSheetDialog()
        }
    }
    private fun restoreState(stateHolder: StateHolder) {
        stateHolder.selectedMealTimeChipId?.let { binding.mealtimeChipgroupFilter.check(it) }
        stateHolder.selectedDishesChipId?.let { binding.dishesChipgroupFilter.check(it) }
        binding.seekBarCalories.progress = stateHolder.selectedCalories ?: 0
        binding.seekBarPortions.progress = stateHolder.selectedPortions ?: 0
        binding.seekBarCookingtime.progress = stateHolder.selectedTime ?: 0
    }
    class FilterDataHolder(
        var mealTime: Meals? = null,
        var dishes: Dishes? = null,
        var calories: Int? = null,
        var portions: Int? = null,
        var time: Int? = null
    ): Serializable
    class StateHolder : Serializable {
        var selectedMealTimeChipId: Int? = null
        var selectedDishesChipId: Int? = null
        var selectedCalories: Int? = null
        var selectedPortions: Int? = null
        var selectedTime: Int? = null
    }
}