package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryLogin
import com.example.appteam4.model.response.ResponseLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLogin(private val repositoryLogin: RepositoryLogin = RepositoryLogin()):ViewModel() {
    val data = MutableLiveData<ResponseLogin>()

    fun postLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryLogin.postLogin(email, password)
            data.postValue(result.body())
        }
    }
}
