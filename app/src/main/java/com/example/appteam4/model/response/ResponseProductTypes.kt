package com.example.appteam4.model.response

import com.example.appteam4.data.ProductType
import com.google.gson.annotations.SerializedName

data class ResponseProductTypes (
    @SerializedName("accessToken") val productTypes: List<ProductType>
)