package com.example.appteam4.model.repository

import com.example.appteam4.model.datasource.DataSourceProductTypes
import com.example.appteam4.model.response.ResponseProductTypes
import retrofit2.Response

class RepositoryProductTypes(token: String) {
    private val dataSourceProductTypes: DataSourceProductTypes = DataSourceProductTypes(token)

    suspend fun getProductTypes(): Response<ResponseProductTypes> {
        return dataSourceProductTypes.getProductTypes()
    }
}
