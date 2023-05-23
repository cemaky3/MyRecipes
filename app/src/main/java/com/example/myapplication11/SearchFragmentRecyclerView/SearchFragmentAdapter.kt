package com.example.myapplication11.SearchFragmentRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R

class SearchFragmentAdapter(
    private val itemList: List<DataModel>,
    private val fragmentManager: FragmentManager,
    private val listener: OnCheckedChangeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LIST) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_recipe_item, parent, false)
            RecipeViewHolder(view)
        } else if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_fragment_header_item, parent, false)
            HeaderViewHolder(view)
        } else if (viewType == VIEW_TYPE_RESPONSE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_recipe_item, parent, false)
            RecipeApiResponseViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.searchbar_and_filter_item, parent, false)
            SearchbarViewHolder(view, fragmentManager)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = itemList[position]

        if ((currentItem is DataModel.Recipe) && (holder is RecipeViewHolder)) {
            holder.bind(currentItem, listener)
            holder.favor.setOnCheckedChangeListener { _, isChecked ->
                listener.onCheckedChanged(position, isChecked)
            }
        }
        if ((currentItem is DataModel.RecipeResponse) && (holder is RecipeApiResponseViewHolder)) {
            holder.bind(currentItem)
        }
        if ((currentItem is DataModel.Header) && (holder is HeaderViewHolder)) {
            holder.bind(currentItem)
        }
        if ((currentItem is DataModel.Searchbar) && (holder is SearchbarViewHolder)) {
            holder.bind(currentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is DataModel.Recipe -> VIEW_TYPE_LIST
            is DataModel.RecipeResponse -> VIEW_TYPE_RESPONSE
            is DataModel.Searchbar -> VIEW_TYPE_SEARCHBAR
            is DataModel.Header -> VIEW_TYPE_HEADER
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun getItem(position: Int): DataModel {
        return itemList[position]
    }
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_SEARCHBAR = 1
        const val VIEW_TYPE_LIST = 2
        const val VIEW_TYPE_RESPONSE = 3
    }
}
