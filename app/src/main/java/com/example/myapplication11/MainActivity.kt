package com.example.myapplication11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication11.Fragments.FavoriteFragment
import com.example.myapplication11.Fragments.ProfileFragment
import com.example.myapplication11.Fragments.SearchFragment
import com.example.myapplication11.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_home_container, SearchFragment())
            .commit()

        val botNavView :BottomNavigationView = binding.bottomBar

        val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_container, SearchFragment())
                        .commit()
                    true
                }
                R.id.favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_container, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
        botNavView.setOnNavigationItemSelectedListener(listener)
    }
}