package com.example.myapplication11.Fragments.FragmentNavigation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication11.Fragments.FavoriteFragment
import com.example.myapplication11.Fragments.ProfileFragment
import com.example.myapplication11.Fragments.SearchFragment
import com.example.myapplication11.MainActivity

class MainPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    private val fragmentList = listOf(
        SearchFragment(),
        FavoriteFragment(),
        ProfileFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}