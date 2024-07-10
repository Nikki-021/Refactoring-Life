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
import com.example.appteam4.ui.viewmodel.ResultState
import com.example.appteam4.ui.viewmodel.ViewModelRegister

class MainActivityRegister : AppCompatActivity() {
    private var isPasswordVisible = false
    private val viewModel by viewModels<ViewModelRegister>()
    private lateinit var binding: ActivityMainRegisterBinding
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
        setupTextObservers()
        setupObservers()
        navigation()
        passwordTransformation()
        callRegister()
        observerRegister()
    }

    private fun callRegister() {
        binding.btnenter.setOnClickListener {
            viewModel.postRegister(
                binding.etEmailRegister.text.toString(),
                binding.etPasswordRegister.text.toString()
            )
        }
    }

    private fun observerRegister() {
        viewModel.data.observe(this){
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

    private fun navigation(){
        binding.tvLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setupTextObservers() {
        binding.etEmailRegister.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                it.toString().trim(),
                binding.etPasswordRegister.text.toString().trim()
            )
        }

        binding.etPasswordRegister.doAfterTextChanged { it: Editable? ->
            viewModel.validateFields(
                binding.etEmailRegister.text.toString().trim(),
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
            viewModel.register(
                binding.etEmailRegister.text.toString().trim(),
                binding.etPasswordRegister.text.toString().trim()
            )
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

        binding.vwSquare.setOnClickListener {
            showPassword()
        }
    }

    private fun showPassword() {
        if (isPasswordVisible) {
            binding.etPasswordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            binding.etPasswordRegister.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        isPasswordVisible = !isPasswordVisible
    }
}
