package com.example.appteam4.model.service

import com.example.appteam4.model.response.ResponseLogin
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ServiceLogin {
    @POST("/api/v1/auth/login")
    suspend fun postLogin(@Body request: LoginRequest): Response<ResponseLogin>
}

data class LoginRequest(val email: String, val password: String)