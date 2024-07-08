package com.example.appteam4.model.response

import com.example.appteam4.data.Products
import com.google.gson.annotations.SerializedName

data class ResponseProducts(
    @SerializedName("accessToken") val products: Products
)