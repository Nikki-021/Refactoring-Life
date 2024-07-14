package com.example.appteam4.model.service

import com.example.appteam4.data.Products
import retrofit2.Response
import retrofit2.http.GET


interface ServiceProducts {
    @GET("/api/v1/products")
    suspend fun getProducts(): Response<Products>
}