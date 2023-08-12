package com.example.BookShop.datasource.remote

import com.example.BookShop.data.api.RetrofitClient
import com.example.BookShop.data.model.*
import com.example.BookShop.datasource.IDataSource
import okhttp3.MultipartBody
import retrofit2.Response

class RemoteDataSource() : IDataSource {

    override suspend fun login(email: String, password: String): Response<LoginResponse>? {
        return RetrofitClient.apiService.login(email, password)
    }
    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>? {
        return RetrofitClient.apiService.getSearchProducts(
            limit,
            page,
            description_length,
            query_string,
            filter_type,
            price_sort_order,
        )
    }

    override suspend fun getSearchHistory(query_string: String): Response<ProductList> {
        return RetrofitClient.apiService.getSearchHistory(query_string)
    }

    override suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
    ): Response<ProductList>? {
        return RetrofitClient.apiService.getSearchAuthorProducts(
            authorId,
            limit,
            page,
            description_length,
            query_string,
        )
    }

    override suspend fun getSearchCategoryProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        categoryId: Int,
    ): Response<ProductList>? {
        return RetrofitClient.apiService.getSearchCategoryProducts(
            limit,
            page,
            description_length,
            query_string,
            categoryId,
        )
    }

    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return RetrofitClient.apiService.getProductInfo(id)
    }

    override suspend fun getProductsByAuthor(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>? {
        return RetrofitClient.apiService.getProductsByAuthor(id, limit, page, description_length)
    }

    override suspend fun getProductsByCategory(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList> {
        return RetrofitClient.apiService.getProductsByCategory(id, limit, page, description_length)
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorResult>? {
        return RetrofitClient.apiService.getAuthor(authorId)
    }

    override suspend fun getSearchNewProduct(): Response<ProductNewList>? {
        return RetrofitClient.apiService.getSearchNewProduct()
    }

    override suspend fun getCustomer(): Response<Customer>? {
        return RetrofitClient.apiService.getCustomer()
    }

    override suspend fun updateCustomer(
        name: String,
        address: String,
        dob: String,
        gender: String,
        mob_phone: String,
    ): Response<Customer>? {
        return RetrofitClient.apiService.updateCustomer(name, address, dob, gender, mob_phone)
    }
    override suspend fun changePassword(
        email: String,
        old_password: String,
        new_password: String,
    ): Response<Customer>? {
        return RetrofitClient.apiService.changePassword(email, old_password, new_password)
    }

    override suspend fun changeAvatar(image: MultipartBody.Part): Response<Customer>? {
        return RetrofitClient.apiService.changeAvatar(image)
    }

    override suspend fun getOrderHistory(): Response<OrderList>? {
        return RetrofitClient.apiService.getOrderHistory()
    }

    override suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>? {
        return RetrofitClient.apiService.getOrderDetail(orderId)
    }
    override suspend fun getAllAuthor(): Response<AuthorList>? {
        return RetrofitClient.apiService.getAuthor()
    }

    override suspend fun addCartItem(productId: Int): Response<List<CartItem>>? {
        return RetrofitClient.apiService.addProduct2Cart(productId)
    }
    override suspend fun addItemToWishList(productId: Int): Response<Messeage>? {
        return RetrofitClient.apiService.addItemToWishList(productId)
    }

    override suspend fun removeItemInWishList(productId: Int): Response<Messeage> {
        return RetrofitClient.apiService.removeItemInWishList(productId)
    }

    override suspend fun getCategory(): Response<CategoryList>? {
        return RetrofitClient.apiService.getCategory()
    }
}