package com.example.BookShop.data.repository.product

import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.data.model.ProductsByAuthor
import retrofit2.Response

interface ProductRepository {
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
}