package com.example.appteam4.model.repository

import com.example.appteam4.data.ProductDailyOffer
import com.example.appteam4.model.datasource.DataSourceDailyOffer
import retrofit2.Response

class RepositoryDailyOffer(token: String) {
    private val dataSourceDailyOffer: DataSourceDailyOffer = DataSourceDailyOffer(token)
    suspend fun getProductDailyOffer(): Response<ProductDailyOffer> {
        return dataSourceDailyOffer.getProductDailyOffer()
    }
}
