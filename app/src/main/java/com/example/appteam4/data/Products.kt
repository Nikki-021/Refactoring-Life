package com.example.appteam4.data

data class Products (
    val page: Number,
    val size: Number,
    val totalPages: Number,
    val totalProducts: Number,
    val products: Product
)
data class Product (
    val idProduct: Number,
    val name: String,
    val productType: ProductType,
    val currency: String,
    val price: String,
    val image: String,
    val isFavorite: Boolean,
    val description: String
)
data class ProductType(
    val idProductType: Number,
    val description: String,
)