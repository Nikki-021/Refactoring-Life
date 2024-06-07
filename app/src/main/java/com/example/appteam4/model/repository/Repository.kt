package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSource
import com.example.appteam4.model.response.ResponseData
import retrofit2.Response

class Repository(private val dataSource: DataSource = DataSource()) {
    suspend fun getData(): Response<ResponseData> {
        return dataSource.getData()
    }
}