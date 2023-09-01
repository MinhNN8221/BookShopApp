package com.example.BookShop.data.repository.product

import com.example.BookShop.data.model.*
import retrofit2.Response

interface ProductRepository {
    suspend fun getProductsBanner(): Response<BannerList>?
    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
    suspend fun getProductsByAuthor(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>?

    suspend fun getProductsByCategory(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>?

    suspend fun getProductsBySupplier(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>?

    suspend fun getNewBook(): Response<BookInHomeList>?
    suspend fun getHotBook(): Response<BookInHomeList>?
}