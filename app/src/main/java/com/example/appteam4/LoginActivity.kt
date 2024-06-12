package com.example.appteam4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appteam4.databinding.ActivityLoginBinding
import com.example.appteam4.ui.viewmodel.ViewModelLogin

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<ViewModelLogin>()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        callLogin()
        observerLogin()
        navigation()
    }

    private fun callLogin() {
        val email = binding.etEmail.toString()
        val password = binding.etPassword.toString()

        viewModel.postLogin(email = email, password = password)
    }

    private fun observerLogin() {
        viewModel.data.observe(this) {
            val token = it.token
        }
    }

    private fun navigation() {

    }
}
