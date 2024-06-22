package com.example.appteam4

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.appteam4.databinding.ActivityLoginBinding
import com.example.appteam4.ui.viewmodel.LoginViewModel
import com.example.appteam4.ui.viewmodel.ViewModelLogin

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<ViewModelLogin>()
    private val loginViewModel: LoginViewModel by viewModels()
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
        sentInfo()
        setupTextObservers()
        setupObservers()
    }

    private fun sentInfo() {
        binding.btnGetIn1.setOnClickListener {
            callLogin()
            observerLogin()
        }
    }

    private fun callLogin() {
        viewModel.postLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
    }

    private fun observerLogin() {
        viewModel.data.observe(this) {
            it.token
        }
    }

    private fun setupTextObservers() {
        binding.etEmail.doAfterTextChanged { it: Editable? ->
            loginViewModel.validateFields(
                it.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }

        binding.etPassword.doAfterTextChanged { it: Editable? ->
            loginViewModel.validateFields(
                binding.etEmail.text.toString().trim(),
                it.toString().trim()
            )
        }
    }

    private fun setupObservers() {
        loginViewModel.emailError.observe(this) { error ->
            binding.etEmail.error = error
        }

        loginViewModel.passwordError.observe(this) { error ->
            binding.etPassword.error = error
        }

        loginViewModel.isLoginButtonEnabled.observe(this) { isEnabled ->
            binding.btnGetIn1.isEnabled = isEnabled
            updateLoginButtonColor(isEnabled)
        }

        loginViewModel.loginResult.observe(this) { success ->
            if (success) {
                // Navegar a la siguiente pantalla
                Toast.makeText(this, "Inicio de sesión exitosa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGetIn1.setOnClickListener {
            loginViewModel.login()
        }
    }

    private fun updateLoginButtonColor(isEnabled: Boolean) {
        if (isEnabled) {
            binding.btnGetIn1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.enabled_button_color
                )
            )
        } else {
            binding.btnGetIn1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.disabled_button_color
                )
            )
        }
    }
}
