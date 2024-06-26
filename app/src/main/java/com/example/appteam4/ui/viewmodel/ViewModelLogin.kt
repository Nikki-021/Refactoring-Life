package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appteam4.model.repository.RepositoryLogin
import com.example.appteam4.model.response.ResponseLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLogin(private val repositoryLogin: RepositoryLogin = RepositoryLogin()) :
    ViewModel() {
    val data = MutableLiveData<ResponseLogin>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun postLogin(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val result = repositoryLogin.postLogin(email, password)
                loading.postValue(false)
                if (result.isSuccessful) {
                    data.postValue(result.body())
                } else {
                    error.postValue(result.message())
                }
            } catch (e: Exception) {
                loading.postValue(false)
                error.postValue(e.message ?: "Error desconocido")
            }
        }
    }
}

