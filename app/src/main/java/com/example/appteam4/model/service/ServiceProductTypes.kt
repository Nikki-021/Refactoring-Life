package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseProductTypes
import retrofit2.Response
import retrofit2.http.GET


interface ServiceProductTypes {
    @GET("/api/v1/product-types")
    suspend fun getProductTypes(): Response<ResponseProductTypes>
}

