package com.example.appteam4.data

data class Products (
    val page: Number,
    val size: Number,
    val totalPages: Number,
    val totalProducts: Number,
    val products: List<Product>
)

data class Product (
    val idProduct: Number,
    val name: String,
    val productType: ProductType,
    val currency: String,
    val price: String,
    val image: String,
    val isFavorite: Boolean,
    val description: String,
)

data class ProductType (
    val idProductType: Number,
    val description: String,
)

data class ImageReq (
    val link : String,
    val provider : String,
    val principal : Boolean,
)

data class ImageRes (
    val link : String,
)

data class ProductId (
    val idProduct: Number,
    val name: String,
    val productType: ProductType,
    val currency: String,
    val price: String,
    val image: List<ImageRes>,
    val isFavorite: Boolean,
    val description: String,
    val largeDescription : String,
)