package com.example.appteam4.model.repository

import com.example.appteam4.data.Products
import com.example.appteam4.model.datasource.DataSourceProducts
import retrofit2.Response

class RepositoryProducts(token : String) {
    private val dataSourceProducts: DataSourceProducts = DataSourceProducts(token)
    suspend fun getProducts(): Response<Products> {
        return dataSourceProducts.getProducts()
    }
}
