package com.example.appteam4.model.datasource

import com.example.appteam4.data.ProductDailyOffer
import com.example.appteam4.model.Intercept.AuthInterceptor
import com.example.appteam4.model.service.ServiceDailyOffer
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceDailyOffer(token: String) {

    private val url = "https://api-products-fe4p.onrender.com"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceData = retrofit.create(
        ServiceDailyOffer::class.java
    )

    suspend fun getProductDailyOffer(): Response<ProductDailyOffer> {
        return serviceData.getProductDailyOffer()
    }
}
