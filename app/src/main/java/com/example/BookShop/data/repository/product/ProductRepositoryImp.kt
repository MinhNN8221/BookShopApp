package com.example.BookShop.data.repository.product

import com.example.BookShop.data.repository.product.ProductRepository
import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class ProductRepositoryImp(private val dataSource: IDataSource) : ProductRepository {
    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return dataSource.getProductInfo(id)
    }
}