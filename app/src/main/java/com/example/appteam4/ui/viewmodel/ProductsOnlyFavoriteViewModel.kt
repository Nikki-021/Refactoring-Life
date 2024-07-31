package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.data.Products
import com.example.appteam4.model.repository.RepositoryProductsOnlyFavorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsOnlyFavoriteViewModel(token: String) : ViewModel() {

    private val repositoryProductsOnlyFavorite: RepositoryProductsOnlyFavorite = RepositoryProductsOnlyFavorite(token)
    val data = MutableLiveData<Products>()

    fun getOnlyFavorite() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryProductsOnlyFavorite.getOnlyFavorite()
            data.postValue(result.body())
        }
    }
}
