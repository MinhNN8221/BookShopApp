package com.example.BookShop.data.repository.product

import com.example.BookShop.data.repository.product.ProductRepository
import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.data.model.ProductsByAuthor
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class ProductRepositoryImp(private val dataSource: IDataSource) : ProductRepository {
    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return dataSource.getProductInfo(id)
    }

    override suspend fun getProductsByAuthor(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>? {
        return dataSource.getProductsByAuthor(author_id, limit, page, description_length)
    }

    override suspend fun getProductsByCategory(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>? {
        return dataSource.getProductsByCategory(author_id, limit, page, description_length)
    }

    override suspend fun getProductsBySupplier(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>? {
        return dataSource.getProductsBySupplier(id, limit, page, description_length)
    }
}