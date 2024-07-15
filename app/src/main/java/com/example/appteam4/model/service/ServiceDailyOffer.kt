package com.example.appteam4.model.service

import com.example.appteam4.data.ProductDailyOffer
import retrofit2.Response
import retrofit2.http.GET


interface ServiceDailyOffer {
    @GET("/api/v1/products/daily-offer")
    suspend fun getProductDailyOffer(): Response<ProductDailyOffer>
}

