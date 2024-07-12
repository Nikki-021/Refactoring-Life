package com.example.appteam4.model.datasource

import com.example.appteam4.model.response.ResponseProducts
import com.example.appteam4.model.service.ServiceProducts
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProducts {
    private val url = "https://api-products-fe4p.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceProducts::class.java
    )

    suspend fun getProducts(): Response<ResponseProducts> {
        return serviceData.getProducts()
    }
}
