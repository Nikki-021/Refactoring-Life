package com.example.appteam4.model.datasource

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.appteam4.model.response.ResponseData
import com.example.appteam4.model.service.HomeServiceLogin
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceLogin {
    private val url = "https://api-users-c9xg.onrender.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceData = retrofit.create(HomeServiceLogin
    ::class.java)
    suspend fun getToken(): Response<ResponseData> {
        return serviceData.getToken()
    }
    suspend fun postLogin(email: String, password: String): Response<ResponseData>{
        return serviceData.postLogin(email = email, password = password)
    }
}