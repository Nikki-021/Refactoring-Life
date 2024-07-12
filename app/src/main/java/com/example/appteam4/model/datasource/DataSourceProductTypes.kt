package com.example.appteam4.model.datasource

import android.util.Log
import com.example.appteam4.model.Intercept.AuthInterceptor
import com.example.appteam4.model.response.ResponseProductTypes
import com.example.appteam4.model.service.ServiceProductTypes
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*class DataSourceProductTypes {
    private val url = "https://api-products-fe4p.onrender.com"
    private val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val serviceData = retrofit.create(
        ServiceProductTypes::class.java
    )

    suspend fun getProductTypes(): Response<ResponseProductTypes> {
        return serviceData.getProductTypes()
    }
}*/

class DataSourceProductTypes(private val token: String) {

    private val url = "https://api-products-fe4p.onrender.com"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceData = retrofit.create(ServiceProductTypes::class.java)

    suspend fun getProductTypes(): Response<ResponseProductTypes> {
        val response = serviceData.getProductTypes()
        Log.d("DataSourceProductTypes", "Response: ${response.body()}")
        return response
    }
}

