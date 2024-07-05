package com.example.appteam4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appteam4.databinding.ActivityMainRegisterBinding
import androidx.activity.viewModels
import com.example.appteam4.ui.viewmodel.RegisterEvent
import com.example.appteam4.ui.viewmodel.RegisterViewModel

class MainActivityRegister : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel>()
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
        binding.btEnter.setOnClickListener {
            viewModel.postRegister(
                binding.etEmailRegister.text.toString(),
                binding.etPasswordRegister.text.toString()
            )
        }
    }

    private fun observerRegister() {
        viewModel.data.observe(this){
            when(it){
                is RegisterEvent.Success -> {
                    binding.viewProgressBar.view.visibility = View.GONE
                    binding.viewProgressBar.progressBar.visibility = View.GONE
                    startActivity(Intent(this, LoginActivity::class.java))
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
}
