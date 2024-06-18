package com.example.appteam4

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceRegister {
    @POST("/api/v1/auth/register")
    suspend fun postRegister(@Body email: String, password: String): Response<ResponseRegister>
}

data class RegisterRequest(val email: String, val password: String)
