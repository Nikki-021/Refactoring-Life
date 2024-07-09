package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceProduct
import com.example.appteam4.model.response.ResponseProduct
import retrofit2.Response

class RepositoryProduct(private val dataSourceProduct: DataSourceProduct = DataSourceProduct()) {
    suspend fun getProduct(idProduct: Number): Response<ResponseProduct> {
        return dataSourceProduct.getProduct(idProduct)
    }
}
