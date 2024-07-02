package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appteam4.model.repository.RepositoryLogin
import kotlinx.coroutines.launch

class ViewModelLogin(private val repositoryLogin: RepositoryLogin = RepositoryLogin()) :
    ViewModel() {

    private val _loginState = MutableLiveData<ResultState<String>>()
    val loginState: LiveData<ResultState<String>> get() = _loginState
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult
    private val _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean> get() = _isLoginButtonEnabled

    fun postLogin(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = ResultState.Loading
            try {
                val result = repositoryLogin.postLogin(email, password)
                if (result.isSuccessful) {
                    val responseBody = result.body()
                    if (responseBody != null) {
                        _loginState.postValue(ResultState.Success(responseBody.token))
                    } else {
                        _loginState.postValue(ResultState.Error("Cuerpo de la respuesta nulo"))
                    }
                } else {
                    _loginState.postValue(ResultState.Error("Error en la respuesta"))
                }
            } catch (e: Exception) {
                _loginState.postValue(ResultState.Error(e.message ?: "Error desconocido"))
            }
        }
    }

    fun validateFields(email: String, password: String) {
        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)

        // El botón de login se habilita solo si ambos campos son válidos
        _isLoginButtonEnabled.value = emailValid && passwordValid
    }

    private fun validateEmail(email: String): Boolean {
        // Regex para validar el formato de un correo electrónico
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return when {
            email.isEmpty() -> false
            !email.matches(emailPattern.toRegex()) -> false
            else -> true
        }
    }

    private fun validatePassword(password: String): Boolean {
        return when {
            password.isEmpty() -> false
            password.length < 8 -> false
            !password.matches(".*[A-Z].*".toRegex()) -> false
            !password.matches(".*[a-z].*".toRegex()) -> false
            !password.matches(".*[0-9].*".toRegex()) -> false
            !password.matches(".*[@#\$%^&+=].*".toRegex()) -> false
            else -> true
        }
    }

    fun login(email: String, password: String) {
        if (_isLoginButtonEnabled.value == true) {
            // Simular una operación de login, por ejemplo, usando una corutina
            viewModelScope.launch {
                // Lógica para llamar a la API de login
                val success = simulateLogin(email, password)
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
