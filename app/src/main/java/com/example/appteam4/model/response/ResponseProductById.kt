package com.example.appteam4.model.response

import android.media.Image
import com.example.appteam4.data.ProductTypes
import com.google.gson.annotations.SerializedName

class ResponseProductById (
    @SerializedName("idProduct") val idProduct: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("productType") val productType: ProductTypes?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("images") val images: List<Image>?,
    @SerializedName("description") val description: String?,
    @SerializedName("largeDescription") val largeDescription: String?,
    @SerializedName("isFavorite") val isFavorite: Boolean?
)
