package com.example.appteam4.model.response

import com.example.appteam4.data.ProductId
import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("accessToken") val productId: ProductId)