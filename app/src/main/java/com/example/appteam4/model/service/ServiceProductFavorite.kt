package com.example.appteam4.model.service

import com.example.appteam4.data.ProductFavorite
import retrofit2.Response
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServiceProductFavorite {
    @PUT("/api/v1/products/{idProduct}/favorite")
    suspend fun updateProductFavorite(
        @Path("idProduct") idProduct: Int
    ): Response<ProductFavorite>
}