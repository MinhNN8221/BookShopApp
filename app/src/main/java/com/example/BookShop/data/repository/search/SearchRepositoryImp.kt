package com.example.BookShop.data.repository.search

import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class SearchRepositoryImp(private val dataSource: IDataSource) : SearchRepository {
    override suspend fun getAllProducts(): Response<ProductList>? {
        return dataSource.getProducts()
    }
}