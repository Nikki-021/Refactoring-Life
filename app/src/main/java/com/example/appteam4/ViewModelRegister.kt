package com.example.appteam4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRegister(private val repositoryRegister: RepositoryRegister = RepositoryRegister()):ViewModel() {
    val data = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun postRegister(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryRegister.postRegister(email,password)
            data.postValue(result.body())
        }
    }
}
