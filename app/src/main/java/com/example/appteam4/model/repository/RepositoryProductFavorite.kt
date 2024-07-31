package com.example.appteam4.model.repository

import com.example.appteam4.data.ProductFavorite
import com.example.appteam4.data.Products
import com.example.appteam4.model.datasource.DataSourceProductFavorite
import com.example.appteam4.model.datasource.DataSourceProductsOnlyFavorite
import retrofit2.Response

class RepositoryProductFavorite(token : String) {
    private val dataSourceProductFavorite : DataSourceProductFavorite = DataSourceProductFavorite(token)
    suspend fun updateProductFavorite(idProduct: Int): Response<ProductFavorite> {
        return dataSourceProductFavorite.updateProductFavorite(idProduct)
    }
}
