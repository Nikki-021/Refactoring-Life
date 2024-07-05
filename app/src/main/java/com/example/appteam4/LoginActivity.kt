package com.example.appteam4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appteam4.databinding.ActivityLoginBinding
import com.example.appteam4.ui.viewmodel.LoginEvent
import com.example.appteam4.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        callLogin()
        observerLogin()
    }

    private fun callLogin() {
        binding.btnGetIn1.setOnClickListener {
            viewModel.postLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun observerLogin() {
        viewModel.data.observe(this) {
            when (it) {
                is LoginEvent.Success -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                is LoginEvent.Loading -> {
                    binding.viewProgressBar.view.visibility = View.VISIBLE
                    binding.viewProgressBar.progressBar.visibility = View.VISIBLE
                }

                is LoginEvent.Error -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
