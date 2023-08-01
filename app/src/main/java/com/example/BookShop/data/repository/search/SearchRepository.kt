package com.example.BookShop.data.repository.search

import com.example.BookShop.data.model.Product
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.data.model.ProductNewList
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>?

    suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
    ): Response<ProductList>?

    suspend fun getSearchNewProduct(): Response<ProductNewList>?
}