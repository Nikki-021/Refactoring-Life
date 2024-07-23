package com.example.appteam4.data

data class ProductDailyOffer(
    val idProduct: Int,
    val name: String,
    val productType: ProductType,
    val currency: String,
    val price: Double,
    val images: List<ImageRes>,
    val description: String,
    val isFavorite: Boolean = true,
    val dailyOffer: Boolean = true,
)