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
}
