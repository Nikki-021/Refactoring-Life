package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appteam4.model.repository.RepositoryRegister
import com.example.appteam4.model.response.ResponseRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRegister(private val repositoryRegister: RepositoryRegister = RepositoryRegister()):ViewModel() {

    private val _registerState = MutableLiveData<ResultState<String>>()
    val registerState: LiveData<ResultState<String>> get() = _registerState

    fun postRegister(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = ResultState.Loading
            try {
                val result = repositoryRegister.postRegister(email, password)
                if (result.isSuccessful) {
                    val responseBody = result.body()
                    if (responseBody != null) {
                        _registerState.postValue(ResultState.Success(responseBody.token))
                    } else {
                        _registerState.postValue(ResultState.Error("Cuerpo de la respuesta nulo"))
                    }
                } else {
                    _registerState.postValue(ResultState.Error("Error en la respuesta"))
                }
            } catch (e: Exception) {
                _registerState.postValue(ResultState.Error(e.message ?: "Error desconocido"))
            }
        }
    }
}
