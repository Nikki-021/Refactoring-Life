package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseData
import retrofit2.Response
import retrofit2.http.GET


interface HomeService {
    @GET("image/random")
    suspend fun getImageRandomDogs(): Response<ResponseData>
}