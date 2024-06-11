package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ServiceLogin {
    @POST("/api/v1/auth/login")
    suspend fun postLogin(@Body email: String, password: String): Response<ResponseLogin>
}
