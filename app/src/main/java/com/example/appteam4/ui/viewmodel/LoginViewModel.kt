package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val email = MutableLiveData<String>()
    private val password = MutableLiveData<String>()

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult
    private val _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean> get() = _isLoginButtonEnabled

    fun validateFields(emailInput: String, passwordInput: String) {
        val emailValid = validateEmail(emailInput)
        val passwordValid = validatePassword(passwordInput)

        email.value = emailInput
        password.value = passwordInput

        // El botón de login se habilita solo si ambos campos son válidos
        _isLoginButtonEnabled.value = emailValid && passwordValid
    }

    private fun validateEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                _emailError.value = "Ingrese el email"
                false
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _emailError.value = "Email incorrecto"
                false
            }

            else -> {
                _emailError.value = null
                true
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                _passwordError.value = "Ingrese la contraseña"
                false
            }

            password.length < 8 -> {
                _passwordError.value = "La contraseña debe tener al menos 8 caracteres."
                false
            }

            !password.matches(".*[A-Z].*".toRegex()) -> {
                _passwordError.value = "La contraseña debe contener al menos una letra mayúscula."
                false
            }

            !password.matches(".*[a-z].*".toRegex()) -> {
                _passwordError.value = "La contraseña debe contener al menos una letra minúscula."
                false
            }

            !password.matches(".*[0-9].*".toRegex()) -> {
                _passwordError.value = "La contraseña debe contener al menos un número."
                false
            }

            !password.matches(".*[@#\$%^&+=].*".toRegex()) -> {
                _passwordError.value = "La contraseña debe contener al menos un carácter especial."
                false
            }

            else -> {
                _passwordError.value = null
                true
            }
        }
    }

    fun login() {
        if (_isLoginButtonEnabled.value == true) {
            // Simular una operación de login, por ejemplo, usando una corutina
            viewModelScope.launch {
                // Lógica para llamar a la API de login
                val success = simulateLogin(email.value!!, password.value!!)
                _loginResult.value = success
            }
        } else {
            _loginResult.value = false
        }
    }

    private suspend fun simulateLogin(email: String, password: String): Boolean {
        kotlinx.coroutines.delay(2000)  // Simular un retraso de red
        return email == "usuario@dominio.com" && password == "Password123!"
    }
}
