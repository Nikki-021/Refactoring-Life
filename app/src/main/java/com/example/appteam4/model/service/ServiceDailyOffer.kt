package com.example.appteam4.model.service

import com.example.appteam4.data.ProductId
import com.example.appteam4.model.response.ResponseDailyOffer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT


interface ServiceDailyOffer {
    @PUT("/api/v1/products/daily-offer")
    suspend fun putProductDailyOffer(@Body idProduct: Number): Response<ResponseDailyOffer>
}

