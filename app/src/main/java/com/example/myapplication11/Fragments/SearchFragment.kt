package com.example.myapplication11.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.R
import com.example.myapplication11.Recipe
import com.example.myapplication11.SearchFragmentRecyclerView.DataModel
import com.example.myapplication11.SearchFragmentRecyclerView.OnCheckedChangeListener
import com.example.myapplication11.SearchFragmentRecyclerView.SearchFragmentAdapter
import com.example.myapplication11.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), OnCheckedChangeListener {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.searchFragmentRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)

        val itemList = mutableListOf<DataModel>()
        itemList.apply {
            add(DataModel.Searchbar(getString(R.string.main_title_text)))
            add(DataModel.Header(getString(R.string.pop_recipes), getString(R.string.more_btn)))
            add(
                DataModel.Header(
                    getString(R.string.header_recommended),
                    getString(R.string.more_btn)
                )
            )
        }
        val adapter = SearchFragmentAdapter(itemList, childFragmentManager, this)
        recyclerView.adapter = adapter

        viewModel.localRecipesLD.observe(
            viewLifecycleOwner,
            Observer {
                itemList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        )
        viewModel.apiResponseListLD.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    itemList.addAll(2, it)
                    adapter.notifyDataSetChanged()
                }
            }
        )
        viewModel.errorResponseLD.observe(
            viewLifecycleOwner,
            Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        )
        viewModel.filteredRecipeListLD.observe(
            viewLifecycleOwner,
            Observer {
                itemList.clear()
                itemList.add(DataModel.Searchbar(getString(R.string.main_title_text)))
                itemList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        )
    }

    override fun onCheckedChanged(position: Int, isChecked: Boolean) {
        val adapter = binding.searchFragmentRecyclerView.adapter as SearchFragmentAdapter
        val item = adapter.getItem(position) as DataModel.Recipe
        viewModel.onCheckedChanged(isChecked, item)
    }
}
