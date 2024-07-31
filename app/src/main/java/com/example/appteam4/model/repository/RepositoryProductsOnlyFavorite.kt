package com.example.appteam4.model.repository

import com.example.appteam4.data.Products
import com.example.appteam4.model.datasource.DataSourceProductsOnlyFavorite
import retrofit2.Response

class RepositoryProductsOnlyFavorite(token : String) {
    private val dataSourceProductsOnlyFavorite :  DataSourceProductsOnlyFavorite = DataSourceProductsOnlyFavorite(token)
    suspend fun getOnlyFavorite(): Response<Products> {
        return dataSourceProductsOnlyFavorite.getOnlyFavorite()
    }
}
