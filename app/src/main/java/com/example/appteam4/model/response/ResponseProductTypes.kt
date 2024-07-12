package com.example.appteam4.model.response

import com.example.appteam4.data.ProductTypes
import com.google.gson.annotations.SerializedName

data class ResponseProductTypes(
    @SerializedName("productTypes") val productTypes: List<ProductTypes>
)