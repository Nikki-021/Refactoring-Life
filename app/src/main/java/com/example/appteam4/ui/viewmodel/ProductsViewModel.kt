package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryProducts
import com.example.appteam4.model.response.ResponseProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(token: String) : ViewModel() {

    private val repositoryProducts: RepositoryProducts = RepositoryProducts(token)
    val data = MutableLiveData<ResponseProducts>()

    fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryProducts.getProducts()
            data.postValue(result.body())
        }
    }
}
