package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceDailyOffer
import com.example.appteam4.model.response.ResponseDailyOffer
import retrofit2.Response

class RepositoryDailyOffer(private val dataSourceDailyOffer: DataSourceDailyOffer = DataSourceDailyOffer()) {
    suspend fun putProductDailyOffer(idProduct: Number): Response<ResponseDailyOffer> {
        return dataSourceDailyOffer.putProductDailyOffer(idProduct)
    }
}
