package com.example.myapplication11.SearchFragmentRecyclerView

import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.Fragments.BottomSheetDialog
import com.example.myapplication11.R

class SearchbarViewHolder(v: View, val fragmentManager: FragmentManager) : RecyclerView.ViewHolder(
    v
) {
    val mainTitle: TextView = itemView.findViewById(R.id.main_title)
    val filterButton: ImageButton = itemView.findViewById(R.id.filter_Button)
    val searchView: SearchView = itemView.findViewById(R.id.search_view)

    fun bind(currentItem: DataModel.Searchbar) {
        mainTitle.text = currentItem.header_text
        filterButton.setOnClickListener {
            val filterBottomSheetDialog: BottomSheetDialog = BottomSheetDialog.newInstance()
            filterBottomSheetDialog.show(
                fragmentManager,
                "add_filter_dialog_fragment"
            )
        }
    }
}
