package com.example.appteam4.data

data class Product (
    val idProduct: Number,
    val name: String,
    val productType: ProductTypes,
    val currency: String,
    val price: String,
    val image: String,
    val isFavorite: Boolean,
    val description: String,
)