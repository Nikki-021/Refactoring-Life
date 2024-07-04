package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLogin(private val repositoryLogin: RepositoryLogin = RepositoryLogin()) :
    ViewModel() {

    val data = MutableLiveData<LoginEvent>()
    private val messageError = "Error en el servicio!!"

    fun postLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            data.postValue(LoginEvent.Loading)
            val result = repositoryLogin.postLogin(email, password)

            if (result.body() != null) {
                data.postValue(LoginEvent.Success(result.body()!!))
            } else {
                data.postValue(LoginEvent.Error(messageError))
            }
        }
    }
}
