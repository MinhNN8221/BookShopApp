package com.example.BookShop.data.repository.search

import com.example.BookShop.data.model.Product
import com.example.BookShop.data.model.ProductList
import retrofit2.Response

interface SearchRepository {
    suspend fun getAllProducts(): Response<ProductList>?
}