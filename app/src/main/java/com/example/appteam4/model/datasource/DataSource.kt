package com.example.appteam4.model.datasource

import com.example.appteam4.model.response.ResponseData
import com.example.appteam4.model.service.HomeService
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSource {
    private val url = "https://dog.ceo/api/breeds/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceData = retrofit.create(HomeService
    ::class.java)
    suspend fun getData(): Response<ResponseData> {
        return serviceData.getImageRandomDogs()
    }
}