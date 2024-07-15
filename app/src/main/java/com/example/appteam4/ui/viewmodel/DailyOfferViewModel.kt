package com.example.appteam4.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.data.ProductDailyOffer
import com.example.appteam4.model.repository.RepositoryDailyOffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyOfferViewModel(token: String) : ViewModel() {

    private val repositoryDailyOffer: RepositoryDailyOffer = RepositoryDailyOffer(token)
    val data = MutableLiveData<ProductDailyOffer>()

    fun getProductDailyOffer() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryDailyOffer.getProductDailyOffer()
            data.postValue(result.body())
        }
    }
}
