package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryProduct
import com.example.appteam4.model.response.ResponseProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repositoryProduct: RepositoryProduct = RepositoryProduct()) :
    ViewModel() {

    val data = MutableLiveData<ResponseProduct>()

    fun getProduct(idProduct: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryProduct.getProduct(idProduct)
            data.postValue(result.body())
        }
    }
}
