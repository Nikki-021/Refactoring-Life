package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceRegister
import com.example.appteam4.model.response.ResponseRegister
import retrofit2.Response

class RepositoryRegister(private val dataSourceRegister: DataSourceRegister = DataSourceRegister()) {

    suspend fun postRegister(email: String, password: String): Response<ResponseRegister>{
        return dataSourceRegister.postRegister(email,password)
    }
}
