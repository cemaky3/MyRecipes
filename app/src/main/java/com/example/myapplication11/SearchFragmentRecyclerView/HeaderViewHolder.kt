package com.example.myapplication11.SearchFragmentRecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R

class HeaderViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val header: TextView = v.findViewById(R.id.main_title)
    val more_btn: TextView = v.findViewById(R.id.more_btn)

    fun bind(currentItem: DataModel.Header) {
        header.text = currentItem.header_text
        more_btn.text = currentItem.more_btn_text
    }
}
