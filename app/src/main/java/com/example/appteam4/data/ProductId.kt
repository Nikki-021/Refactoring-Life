package com.example.appteam4.data

data class ProductId (
    val idProduct: Number,
    val name: String,
    val productType: ProductTypes,
    val currency: String,
    val price: String,
    val image: List<ImageRes>,
    val isFavorite: Boolean,
    val description: String,
    val largeDescription : String,
)