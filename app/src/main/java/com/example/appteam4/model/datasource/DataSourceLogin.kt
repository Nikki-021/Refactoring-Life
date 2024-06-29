package com.example.appteam4.model.datasource

import com.example.appteam4.model.response.ResponseLogin
import com.example.appteam4.model.service.LoginRequest
import com.example.appteam4.model.service.ServiceLogin
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceLogin {
    private val url = "https://api-users-c9xg.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceLogin::class.java
    )

    suspend fun postLogin(email: String, password: String): Response<ResponseLogin> {
        return serviceData.postLogin(LoginRequest(email, password))
    }
}
