package com.example.BookShop.datasource.remote

import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.ProductList
import com.example.shopbook.api.RetrofitClient
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class RemoteDataSource() : IDataSource {

    override suspend fun getProducts(): Response<ProductList>? {
        return RetrofitClient.getRetrofitClient()?.getProducts()
    }

    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return RetrofitClient.getRetrofitClient()?.getProductInfo(id)
    }

}