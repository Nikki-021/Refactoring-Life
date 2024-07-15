package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryProductTypes
import com.example.appteam4.model.response.ResponseProductTypes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductTypesViewModel(token: String) : ViewModel() {

    private val repositoryProductTypes: RepositoryProductTypes = RepositoryProductTypes(token)
    val data = MutableLiveData<ResponseProductTypes>()

    fun getProductTypes() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryProductTypes.getProductTypes()
            data.postValue(result.body())
        }
    }
}
