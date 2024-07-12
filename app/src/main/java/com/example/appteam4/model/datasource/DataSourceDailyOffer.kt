package com.example.appteam4.model.datasource

import com.example.appteam4.data.ProductId
import com.example.appteam4.model.response.ResponseDailyOffer
import com.example.appteam4.model.service.ServiceDailyOffer
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceDailyOffer {
    private val url = "https://api-products-fe4p.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceDailyOffer::class.java
    )

    suspend fun putProductDailyOffer(idProduct: Number): Response<ResponseDailyOffer> {
        return serviceData.putProductDailyOffer(idProduct)
    }
}
