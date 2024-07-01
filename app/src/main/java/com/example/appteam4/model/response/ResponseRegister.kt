package com.example.appteam4.model.response

import com.google.gson.annotations.SerializedName
data class ResponseRegister(
    @SerializedName("accessToken") val token: String,
)
