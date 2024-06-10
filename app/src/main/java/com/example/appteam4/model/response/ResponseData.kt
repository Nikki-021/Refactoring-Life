package com.example.appteam4.model.response

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("accessToken") val data: String,
)