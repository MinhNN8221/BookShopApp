package com.example.BookShop.datasource.remote

import com.example.BookShop.data.model.*
import com.example.shopbook.api.RetrofitClient
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class RemoteDataSource() : IDataSource {

    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>? {
        return RetrofitClient.getRetrofitClient()?.getSearchProducts(
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
        description_length: Int,
        query_string: String,
    ): Response<ProductList>? {
        return RetrofitClient.getRetrofitClient()?.getSearchAuthorProducts(
            authorId,
            limit,
            page,
            description_length,
            query_string,
        )
    }

    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return RetrofitClient.getRetrofitClient()?.getProductInfo(id)
    }

    override suspend fun getProductsByAuthor(
        id: Int,
        limit: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>? {
        return RetrofitClient.getRetrofitClient()
            ?.getProductsByAuthor(id, limit, description_length)
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorResult>? {
        return RetrofitClient.getRetrofitClient()?.getAuthor(authorId)
    }

    override suspend fun getSearchNewProduct(): Response<ProductNewList>? {
        return RetrofitClient.getRetrofitClient()?.getSearchNewProduct()
    }

    override suspend fun getCustomer(): Response<Customer>? {
        return RetrofitClient.getRetrofitClient()?.getCustomer()
    }

    override suspend fun updateCustomer(
        name: String,
        address: String,
        dob: String,
        gender: String,
        mob_phone: String,
    ): Response<Customer>? {
        return RetrofitClient.getRetrofitClient()
            ?.updateCustomer(name, address, dob, gender, mob_phone)
    }

    override suspend fun changePassword(
        email: String,
        old_password: String,
        new_password: String,
    ): Response<Customer>? {
        return RetrofitClient.getRetrofitClient()?.changePassword(email, old_password, new_password)
    }

    override suspend fun getOrderHistory(): Response<OrderList>? {
        return RetrofitClient.getRetrofitClient()?.getOrderHistory()
    }

    override suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>? {
        return RetrofitClient.getRetrofitClient()?.getOrderDetail(orderId)
    }

}