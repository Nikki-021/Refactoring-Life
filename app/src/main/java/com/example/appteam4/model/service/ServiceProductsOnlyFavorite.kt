package com.example.appteam4.model.service

import com.example.appteam4.data.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ServiceProductsOnlyFavorite {
    @GET("/api/v1/products")
    suspend fun getOnlyFavorite(@Query("onlyFavorite") onlyFavorite: Boolean): Response<Products>
}