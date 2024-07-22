package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appteam4.model.repository.RepositoryRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repositoryRegister: RepositoryRegister = RepositoryRegister()) : ViewModel() {

    val data = MutableLiveData<RegisterEvent>()
    private val messageError = "Error en el servicio"
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> get() = _registerResult
    private val _isRegisterButtonEnabled = MutableLiveData<Boolean>()
    val isRegisterButtonEnabled: LiveData<Boolean> get() = _isRegisterButtonEnabled
    private val registeredUsers = mutableMapOf<String, String>()


    fun postRegister(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            data.postValue(RegisterEvent.Loading)
            val result = repositoryRegister.postRegister(email, password)
            if (result.body() != null) {
                registeredUsers[email] = password
                data.postValue(RegisterEvent.Success(result.body()!!))
            } else {
                data.postValue(RegisterEvent.Error(messageError))
            }
        }
    }

    fun validateFields(email: String, password: String, confirmPassword: String){
        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)
        val passwordsMatch = password == confirmPassword
        _isRegisterButtonEnabled.value = emailValid && passwordValid && passwordsMatch
    }

    private fun validateEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.isNotEmpty() && email.matches(emailPattern.toRegex())
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() &&
                password.length >= 8 &&
                password.matches(".*[A-Z].*".toRegex()) &&
                password.matches(".*[a-z].*".toRegex()) &&
                password.matches(".*[0-9].*".toRegex()) &&
                password.matches(".*[@#\$%^&+=].*".toRegex())
    }

    fun register(email: String, password: String) {
        if (_isRegisterButtonEnabled.value == true ) {
            viewModelScope.launch {
                val success = simulateregister(email, password)
                if (success) {
                    // Guardar usuario registrado en la estructura de datos local
                    registeredUsers[email] = password
                }
                _registerResult.value = success
            }
        } else {
            _registerResult.value = false
        }
    }

    private suspend fun simulateregister(email: String, password: String): Boolean {
        // Llamar a API de registro
        kotlinx.coroutines.delay(2000)
        return true
    }

    fun getRegisteredUsers(): Map<String, String> {
        return registeredUsers
    }
}
