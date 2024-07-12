package com.example.appteam4.model.datasource

import com.example.appteam4.model.response.ResponseProduct
import com.example.appteam4.model.service.ServiceProduct
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProduct {
    private val url = "https://api-products-fe4p.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceProduct::class.java
    )

    suspend fun getProduct(idProduct: Number): Response<ResponseProduct> {
        return serviceData.getProduct(idProduct)
    }
}
