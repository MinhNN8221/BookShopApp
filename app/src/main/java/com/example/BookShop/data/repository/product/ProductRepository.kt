package com.example.BookShop.data.repository.product

import com.example.BookShop.data.model.ProductInfoList
import retrofit2.Response

interface ProductRepository {
    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
}