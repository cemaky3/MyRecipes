package com.example.myapplication11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Timber.d("ono","создается")
        val button = findViewById<Button>(R.id.button_next)
        button.setOnClickListener { val intent =Intent (this,MainActivity::class.java)
        startActivity(intent)}
    }
    }
