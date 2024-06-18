package com.example.appteam4

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceRegister {
    private val url = "https://api-users-c9xg.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceRegister::class.java
    )

    suspend fun postRegister(email: String, password: String): Response<ResponseRegister>{
        return serviceData.postRegister(email, password)
    }
}