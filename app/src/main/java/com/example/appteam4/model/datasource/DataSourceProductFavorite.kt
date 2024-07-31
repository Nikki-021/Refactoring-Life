package com.example.appteam4.model.datasource

import com.example.appteam4.data.ProductFavorite
import com.example.appteam4.data.Products
import com.example.appteam4.model.Intercept.AuthInterceptor
import com.example.appteam4.model.service.ServiceProductFavorite
import com.example.appteam4.model.service.ServiceProductsOnlyFavorite
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProductFavorite(token: String) {
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
        ServiceProductFavorite::class.java
    )

    suspend fun updateProductFavorite(idProduct: Int): Response<ProductFavorite> {
        val response = serviceData.updateProductFavorite(idProduct)
        return response
    }
}
