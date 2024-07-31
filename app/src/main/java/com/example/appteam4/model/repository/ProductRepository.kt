package com.example.appteam4.model.repository

class ProductRepository (private val service: ProductServiceImp = ProductServiceImp()) {

    suspend fun getInfoHome(): ObjectComplete {
        val resultProductTypes = service.getProductTypes()
        val resultProducts = service.getProducts()
        val resultDailyOffer = service.getDailyOffer()
        val objectComplete = ObjectComplete(
            productTypes = resultProductTypes,
            products = resultProducts,
            dailyOffer = resultDailyOffer
        )
        return objectComplete
    }

    suspend fun updateFavoriteProduct(productId: Int): Response<ProductByIdResponse> {
        return service.updateFavoriteProduct(productId)
    }

    suspend fun getProductById(id: Int): Response<ProductByIdResponse> {
        return service.getProductById(id)
    }

    suspend fun searchProducts(idProductType: Int,productName: String, onlyFavorite: Boolean): Response<ProductsSearchResponse> {
        return service.searchProducts(idProductType,productName,onlyFavorite)
    }

    suspend fun getSimilarProductsById(id: Int, page: Int, size: Int): Response<ProductsResponse> {
        return service.getSimilarProductsById(id, page, size)
    }
}

data class ObjectComplete(
    val productTypes: Response<ProductTypesResponse>,
    val products: Response<ProductsResponse>,
    val dailyOffer: Response<SingleProductResponse>
)
