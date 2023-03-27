package com.example.myapplication11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.example.myapplication11.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regBtn.setOnClickListener {
            if(checkFields()){
            val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        val spannableString = SpannableString(binding.toLoginActivity.text)

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
        spannableString.setSpan(clickableSpan.underlying,22,27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.toLoginActivity.text = spannableString
        binding.toLoginActivity.movementMethod = LinkMovementMethod.getInstance()

    }
    private fun checkFields(): Boolean {
        return when {
            binding.nameFieldEt.text.toString().length < 3 -> {
                binding.nameField.error = "Имя не короче 3 символов"
                false
            }
            !binding.emailFieldEt.text.toString().contains('@') -> {
                binding.emailField.error = "Необходим символ \"@\""
                false
            }
            binding.emailFieldEt.text.toString().length < 5 -> {
                binding.passwordField.error = "Минимум 5 символов"
                false
            }
            binding.passwordFieldEt.text.toString().length < 6 -> {
                binding.passwordField.error = "Пароль не короче 6 символов"
                false
            }
            else -> true
        }
    }
}