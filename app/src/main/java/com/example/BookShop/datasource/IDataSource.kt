package com.example.BookShop.datasource

import com.example.BookShop.data.model.*
import okhttp3.MultipartBody
import retrofit2.Response

interface IDataSource {
    suspend fun login(email: String, password: String): Response<AuthResponse>?
    suspend fun forgotPassword(email: String): Response<Message>
    suspend fun register(email: String, name: String, password: String): Response<AuthResponse>
    suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>?

    suspend fun getSearchHistory(
        query_string: String,
    ): Response<ProductList>

    suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
    ): Response<ProductList>?

    suspend fun getSearchCategoryProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        categoryId: Int,
    ): Response<ProductList>?

    suspend fun getSearchSupplierProducts(
        supplierId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
    ): Response<ProductList>?

    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
    suspend fun getAllAuthor(): Response<AuthorList>?
    suspend fun getProductsByAuthor(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>?

    suspend fun getProductsByCategory(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>?

    suspend fun getProductsBySupplier(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>?

    suspend fun getAuthor(authorId: Int): Response<AuthorResult>?
    suspend fun getSearchNewProduct(): Response<ProductNewList>?
    suspend fun getCustomer(): Response<Customer>?
    suspend fun updateCustomer(
        name: String,
        address: String,
        dob: String,
        gender: String,
        mob_phone: String,
    ): Response<Customer>?

    suspend fun changePassword(
        email: String, old_password: String,
        new_password: String,
    ): Response<Customer>?

    suspend fun changeAvatar(image: MultipartBody.Part): Response<Customer>?
    suspend fun getOrderHistory(): Response<OrderList>?
    suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>?
    suspend fun addCartItem(productId: Int): Response<List<CartItem>>?
    suspend fun addItemToWishList(productId: Int): Response<Message>?
    suspend fun addAllItemToCart(): Response<Message>
    suspend fun deleteAllItemCart(): Response<Message>
    suspend fun changeProductQuantityInCart(itemId: Int, quantity: Int): Response<Message>?
    suspend fun removeItemInCart(itemId: Int): Response<Message>?
    suspend fun removeItemInWishList(productId: Int): Response<Message>
    suspend fun getWishList(
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<WishlistResponse>?

    suspend fun getAllCart(): Response<Cart>?
    suspend fun getCategory(): Response<CategoryList>?
    suspend fun createOrder(
        cartId: String,
        shippingId: Int,
        address: String,
        receiverName: String,
        receiverPhone: String,
    ): Response<Message>
}