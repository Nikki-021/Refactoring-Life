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
import com.example.appteam4.databinding.ActivityMainRegisterBinding
import com.example.appteam4.ui.viewmodel.RegisterEvent
import com.example.appteam4.ui.viewmodel.RegisterViewModel

class MainActivityRegister : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: ActivityMainRegisterBinding
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        callRegister()
        observerRegister()
        setupTextObservers()
        setupObservers()
        passwordTransformation()
        confirmPasswordTransformation()
        navigation()
    }

    private fun callRegister() {
        binding.btEnter.setOnClickListener {
            viewModel.postRegister(
                binding.etEmailRegister.text.toString(),
                binding.etPasswordRegister.text.toString()
            )
        }
    }

    private fun observerRegister() {
        viewModel.data.observe(this) {
            when (it) {
                is RegisterEvent.Success -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    startActivity(Intent(this, HomeActivity::class.java))
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                }

                is RegisterEvent.Loading -> {
                    binding.viewProgressBar.progressBar.visibility = View.VISIBLE
                    binding.viewProgressBar.view.visibility = View.VISIBLE
                }

                is RegisterEvent.Error -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigation() {
        binding.tvEnter.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("registeredUsers", HashMap(viewModel.getRegisteredUsers()))
            startActivity(intent)
        }
    }

    private fun setupTextObservers() {
        binding.etEmailRegister.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                it.toString().trim(),
                binding.etPasswordRegister.text.toString().trim(),
                binding.etConfirmpasswordRegister.text.toString().trim()
            )
        }

        binding.etPasswordRegister.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                binding.etEmailRegister.text.toString().trim(),
                it.toString().trim(),
                binding.etConfirmpasswordRegister.text.toString().trim()
            )
        }

        binding.etConfirmpasswordRegister.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                binding.etEmailRegister.text.toString().trim(),
                binding.etPasswordRegister.text.toString().trim(),
                it.toString().trim()
            )
        }
    }

    private fun setupObservers() {
        viewModel.isRegisterButtonEnabled.observe(this) { isEnabled ->
            binding.btEnter.isEnabled = isEnabled
            updateRegisterButtonColor(isEnabled)
        }
    }

    private fun updateRegisterButtonColor(isEnabled: Boolean) {
        if (isEnabled) {
            binding.btEnter.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.enabled_button_color
                )
            )
        } else {
            binding.btEnter.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.disabled_button_color
                )
            )
        }
    }

    private fun passwordTransformation() {
        // Tipo de entrada inicial para el campo de contraseña
        binding.etPasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
        // Agregar listener de clic al botón
        binding.vwSquare.setOnClickListener {
            showPassword()
        }
    }

    private fun confirmPasswordTransformation() {
        // Tipo de entrada inicial para el campo de contraseña
        binding.etConfirmpasswordRegister.transformationMethod =
            PasswordTransformationMethod.getInstance()
        // Agregar listener de clic al botón
        binding.vwSquare2.setOnClickListener {
            showPasswordConfirm()
        }
    }

    private fun showPassword() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            binding.etPasswordRegister.transformationMethod =
                PasswordTransformationMethod.getInstance()
        } else {
            // Mostrar contraseña
            binding.etPasswordRegister.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
        isPasswordVisible = !isPasswordVisible // Estado de visibilidad
    }

    private fun showPasswordConfirm() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            binding.etConfirmpasswordRegister.transformationMethod =
                PasswordTransformationMethod.getInstance()
        } else {
            // Mostrar contraseña
            binding.etConfirmpasswordRegister.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
        isPasswordVisible = !isPasswordVisible // Estado de visibilidad
    }
}
