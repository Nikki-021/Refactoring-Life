package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.data.ProductFavorite
import com.example.appteam4.data.Products
import com.example.appteam4.model.repository.RepositoryProductFavorite
import com.example.appteam4.model.repository.RepositoryProductsOnlyFavorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductFavoriteViewModel(token: String) : ViewModel() {

    private val repositoryProductFavorite : RepositoryProductFavorite = RepositoryProductFavorite(token)
    val data = MutableLiveData<ProductFavorite>()

    fun updateProductFavorite(idProduct: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryProductFavorite.updateProductFavorite(idProduct)
            data.postValue(result.body())
        }
    }
}
