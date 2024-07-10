package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appteam4.model.repository.RepositoryRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRegister(private val repositoryRegister: RepositoryRegister = RepositoryRegister()) : ViewModel() {



    val data = MutableLiveData<RegisterEvent>()
    private val messageError = "Error en el servicio"
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> get() = _registerResult
    private val _isRegisterButtonEnable = MutableLiveData<Boolean>()
    val isRegisterButtonEnable: LiveData<Boolean> get() = _isRegisterButtonEnable

    fun postRegister(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            data.postValue(RegisterEvent.Loading)
            val result = repositoryRegister.postRegister(email, password)
            if (result.body() != null) {
                data.postValue(RegisterEvent.Success(result.body()!!))
            } else {
                data.postValue(RegisterEvent.Error(messageError))
            }
        }
    }
    fun validateFields(email: String, password: String){
        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)

        _isRegisterButtonEnable.value = emailValid && passwordValid
    }

    private fun validateEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return when {
            email.isEmpty() -> false
            !email.matches(emailPattern.toRegex()) -> false
            else -> true
        }
    }

    private fun validatePassword(password: String): Boolean{
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
    fun register(email: String, password: String){
        if (_isRegisterButtonEnable.value == true) {
            viewModelScope.launch {
                val success = simulateRegister(email, password)
                _registerResult.value = success
            }
        } else {
            _registerResult.value = false
        }
    }
    private suspend fun simulateRegister(email: String,password: String): Boolean {
        kotlinx.coroutines.delay(2000)
        return email == "usuario@dominio.com" && password == "password123!"
    }
}
