package com.example.appteam4

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appteam4.databinding.ActivityMainRegisterBinding
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.appteam4.ui.viewmodel.RegisterEvent
import com.example.appteam4.ui.viewmodel.RegisterViewModel

class MainActivityRegister : AppCompatActivity() {
    private var isPasswordVisible = false
    private lateinit var binding: ActivityMainRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRegisterBinding.inflate(layoutInflater)
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
        callRegister()
        observerRegister()
    }

    private fun callRegister() {
        binding.btnenter.setOnClickListener {
            val email = binding.etEmailRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()
            val confirmPassword = binding.etConfirmpasswordRegister.text.toString()

            if (password == confirmPassword) {
                viewModel.postRegister(email, password)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observerRegister() {
        viewModel.data.observe(this) {
            when (it) {
                is RegisterEvent.Success -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                is RegisterEvent.Loading -> {
                    binding.viewProgressBar.view.visibility = View.VISIBLE
                    binding.viewProgressBar.progressBar.visibility = View.VISIBLE
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
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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
        viewModel.isRegisterButtonEnable.observe(this) { isEnabled ->
            binding.btnenter.isEnabled = isEnabled
            updateRegisterButtonColor(isEnabled)
        }
        viewModel.registerResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnenter.setOnClickListener {
            val email = binding.etEmailRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val confirmPassword = binding.etConfirmpasswordRegister.text.toString().trim()

            if (password == confirmPassword) {
                viewModel.register(email, password)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRegisterButtonColor(isEnabled: Boolean) {
        if (isEnabled) {
            binding.btnenter.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.enabled_button_color
                )
            )
        } else {
            binding.btnenter.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.disabled_button_color
                )
            )
        }
    }

    private fun passwordTransformation() {
        binding.etPasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.etConfirmpasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.vwSquare.setOnClickListener {
            showPassword()
        }
    }

    private fun showPassword() {
        if (isPasswordVisible) {
            binding.etPasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etConfirmpasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            binding.etPasswordRegister.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etConfirmpasswordRegister.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        isPasswordVisible = !isPasswordVisible
    }
}
