package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.appteam4.model.repository.RepositoryLogin
import com.example.appteam4.model.response.ResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLogin(private val repositoryLogin: RepositoryLogin = RepositoryLogin()) {
    val data = MutableLiveData<ResponseData>()
    fun getToken() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryLogin.getToken()
            data.postValue(result.body())
        }
    }

    fun postLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryLogin.postLogin(email, password)
            data.postValue(result.body())
        }
    }
}