package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseProducts
import retrofit2.Response
import retrofit2.http.GET


interface ServiceProducts {
    @GET("/api/v1/products")
    suspend fun getProducts(): Response<ResponseProducts>
}

