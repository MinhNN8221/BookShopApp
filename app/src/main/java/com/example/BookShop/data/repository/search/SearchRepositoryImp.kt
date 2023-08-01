package com.example.BookShop.data.repository.search

import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.data.model.ProductNewList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class SearchRepositoryImp(private val dataSource: IDataSource) : SearchRepository {
    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>? {
        return dataSource.getSearchProducts(
            limit,
            page,
            description_length,
            query_string,
            filter_type,
            price_sort_order,
        )
    }

    override suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
    ): Response<ProductList>? {
        return dataSource.getSearchAuthorProducts(
            authorId,
            limit,
            page,
            descriptionLength,
            queryString,
        )
    }

    override suspend fun getSearchNewProduct(): Response<ProductNewList>? {
        return dataSource.getSearchNewProduct()
    }
}