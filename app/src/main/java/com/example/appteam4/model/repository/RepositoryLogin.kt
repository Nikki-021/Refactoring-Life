package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceLogin
import com.example.appteam4.model.response.ResponseLogin
import retrofit2.Response

class RepositoryLogin(private val dataSourceLogin: DataSourceLogin = DataSourceLogin()) {
    suspend fun postLogin(email: String, password: String): Response<ResponseLogin> {
        return dataSourceLogin.postLogin(email, password)
    }
}
