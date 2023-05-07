package com.example.myapplication11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication11.Fragments.FavoriteFragment
import com.example.myapplication11.Fragments.FragmentNavigation.MainPagerAdapter
import com.example.myapplication11.Fragments.ProfileFragment
import com.example.myapplication11.Fragments.SearchFragment
import com.example.myapplication11.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botNavView: BottomNavigationView = binding.bottomBar
        viewPager = findViewById(R.id.ViewPager)

        val mainPagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = mainPagerAdapter


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                botNavView.menu.getItem(position).isChecked = true
            }
        })

        val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.favorites -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.profile -> {
                    viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }
        botNavView.setOnNavigationItemSelectedListener(listener)
    }
}