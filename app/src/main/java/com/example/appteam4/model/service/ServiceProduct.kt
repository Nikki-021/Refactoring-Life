package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ServiceProduct {
    @GET("/api/v1/products/{idProduct}")
    suspend fun getProduct(@Path("idProduct") idProduct: Number): Response<ResponseProduct>
}

