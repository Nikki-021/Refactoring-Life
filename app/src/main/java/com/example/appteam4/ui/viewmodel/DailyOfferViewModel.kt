package com.example.appteam4.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteam4.model.repository.RepositoryDailyOffer
import com.example.appteam4.model.repository.RepositoryProduct
import com.example.appteam4.model.response.ResponseDailyOffer
import com.example.appteam4.model.response.ResponseProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyOfferViewModel(private val repositoryDailyOffer: RepositoryDailyOffer = RepositoryDailyOffer()) :
    ViewModel() {

    val data = MutableLiveData<ResponseDailyOffer>()

    fun putProductDailyOffer(idProduct: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryDailyOffer.putProductDailyOffer(idProduct)
                data.postValue(result.body())
        }
    }
}
