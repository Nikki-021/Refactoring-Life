package com.example.appteam4

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.appteam4.databinding.ActivityLoginBinding
import com.example.appteam4.ui.viewmodel.LoginEvent
import com.example.appteam4.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    
    private var isPasswordVisible = false
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
        setupTextObservers()
        setupObservers()
        navigation()
        passwordTransformation()
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

    private fun navigation() {
        binding.tvRegisterHere.setOnClickListener {
            val intent = Intent(this, MainActivityRegister::class.java)
            startActivity(intent)
        }
    }

    private fun setupTextObservers() {
        binding.etEmail.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                it.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }

        binding.etPassword.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                binding.etEmail.text.toString().trim(),
                it.toString().trim()
            )
        }
    }

    private fun setupObservers() {
        viewModel.isLoginButtonEnabled.observe(this) { isEnabled ->
            binding.btnGetIn1.isEnabled = isEnabled
            updateLoginButtonColor(isEnabled)
        }

        viewModel.loginResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error en el login", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGetIn1.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
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

    private fun passwordTransformation() {
        // Tipo de entrada inicial para el campo de contraseña
        binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        // Agregar listener de clic al botón
        binding.vwSquare.setOnClickListener {
            showPassword()
        }
    }

    private fun showPassword() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            // Mostrar contraseña
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        isPasswordVisible = !isPasswordVisible // Estado de visibilidad
    }
}
