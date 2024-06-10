package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceLogin
import com.example.appteam4.model.response.ResponseData
import retrofit2.Response

class RepositoryLogin(private val dataSourceLogin: DataSourceLogin = DataSourceLogin()) {
    suspend fun getToken(): Response<ResponseData> {
        return dataSourceLogin.getToken()
    }

    suspend fun postLogin(email: String, password: String): Response<ResponseData> {
        return dataSourceLogin.postLogin(email, password)
    }
}