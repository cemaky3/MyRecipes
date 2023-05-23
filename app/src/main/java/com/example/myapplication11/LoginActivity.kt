package com.example.myapplication11

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication11.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            if (binding.passwordFieldEt.text.toString()
                    .isNotEmpty() && binding.emailFieldEt.text.toString().isNotEmpty()
            ) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        val spannableString = SpannableString(binding.toRegActivity.text)

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
        spannableString.setSpan(clickableSpan.underlying, 25, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.toRegActivity.text = spannableString
        binding.toRegActivity.movementMethod = LinkMovementMethod.getInstance()
    }
}
