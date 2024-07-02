package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryRegister
import com.example.appteam4.model.response.ResponseRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRegister(private val repositoryRegister: RepositoryRegister = RepositoryRegister()):ViewModel() {
    val data = MutableLiveData<ResponseRegister>()

    fun postRegister(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryRegister.postRegister(email,password)
            data.postValue(result.body())
        }
    }
}
