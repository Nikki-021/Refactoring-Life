package com.example.appteam4.model.datasource

import com.example.appteam4.data.Products
import com.example.appteam4.model.Intercept.AuthInterceptor
import com.example.appteam4.model.service.ServiceProducts
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProducts(token: String) {
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
        ServiceProducts::class.java
    )

    suspend fun getProducts(): Response<Products> {
        val response = serviceData.getProducts()
        return response
    }
}
