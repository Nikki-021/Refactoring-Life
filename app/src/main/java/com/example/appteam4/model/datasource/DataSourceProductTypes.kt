package com.example.appteam4.model.datasource

import com.example.appteam4.model.response.ResponseProductTypes
import com.example.appteam4.model.service.ServiceProductTypes
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProductTypes {
    private val url = "https://api-users-c9xg.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceProductTypes::class.java
    )

    suspend fun getProductTypes(): Response<ResponseProductTypes> {
        return serviceData.getProductTypes()
    }
}
