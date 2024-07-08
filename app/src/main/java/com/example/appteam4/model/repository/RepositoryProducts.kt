package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceLogin
import com.example.appteam4.model.datasource.DataSourceProducts
import com.example.appteam4.model.response.ResponseLogin
import com.example.appteam4.model.response.ResponseProducts
import retrofit2.Response

class RepositoryProducts(private val dataSourceProducts: DataSourceProducts = DataSourceProducts()) {
    suspend fun getProducts(): Response<ResponseProducts> {
        return dataSourceProducts.getProducts()
    }
}
