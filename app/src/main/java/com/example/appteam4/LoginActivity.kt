package com.example.appteam4

import android.os.Bundle
import android.widget.Toast
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
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sentInfo()
        //observerLogin()
    }

    private fun sentInfo() {
        binding.btnGetIn1.setOnClickListener {
            callLogin()
            observerLogin()
        }
    }

    private fun callLogin() {
        viewModel.postLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())

        val text =
            "Hello toast! ${binding.etEmail.text.toString()}, ${binding.etPassword.text.toString()}"
        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
        toast.show()
    }

    private fun observerLogin() {
        viewModel.data.observe(this) {
            it.token
            val text =
                "Hello token! ${it.token}"
            val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
            toast.show()
        }
    }
}
