package com.example.appteam4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appteam4.databinding.ActivityMainRegisterBinding
import androidx.activity.viewModels

class MainActivityRegister : AppCompatActivity() {
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
        callRegister()
        observerRegister()
    }

    private fun callRegister() {
        viewModel.postRegister(
            binding.etEmailRegister.text.toString(),
            binding.etPasswordRegister.text.toString()
        )
    }

    private fun observerRegister() {
        viewModel.data.observe(this) {
            it.token
        }
    }
}
