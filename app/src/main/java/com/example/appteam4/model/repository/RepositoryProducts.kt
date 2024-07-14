package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceProducts
import com.example.appteam4.model.response.ResponseProducts
import retrofit2.Response

class RepositoryProducts(token : String) {
    private val dataSourceProducts: DataSourceProducts = DataSourceProducts(token)
    suspend fun getProducts(): Response<ResponseProducts> {
        return dataSourceProducts.getProducts()
    }
}
