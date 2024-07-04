package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRegister(private val repositoryRegister: RepositoryRegister = RepositoryRegister()) : ViewModel() {

    val data = MutableLiveData<RegisterEvent>()
    private val messageError = "Error en el servicio"

    fun postRegister(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            data.postValue(RegisterEvent.Loading)
            val result = repositoryRegister.postRegister(email, password)
            if (result.body() != null) {
                data.postValue(RegisterEvent.Successs(result.body()!!))
            } else {
                data.postValue(RegisterEvent.Error(messageError))
            }
        }
    }
}
