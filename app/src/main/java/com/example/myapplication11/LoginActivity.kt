package com.example.myapplication11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Timber.d("Main","created")
        val textInputEditText: TextInputEditText?= findViewById(R.id.password_field)
        val txtInputlay: TextInputLayout?= findViewById(R.id.password_field)
        textInputEditText?.addTextChangedListener {
            if (it.toString().length<6)
                txtInputlay?.error = "fdjhlkjdsfjblsdjf"
        }

        }

    }
