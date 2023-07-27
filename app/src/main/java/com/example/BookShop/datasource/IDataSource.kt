package com.example.BookShop.datasource

import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.ProductList
import retrofit2.Response

interface IDataSource {
    suspend fun getProducts(): Response<ProductList>?
    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
}