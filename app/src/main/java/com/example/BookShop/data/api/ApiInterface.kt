package com.example.BookShop.data.api

import com.example.BookShop.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("products/search")
    suspend fun getSearchProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
        @Query("query_string") query_string: String,
        @Query("filter_type") filter_type: Int,
        @Query("price_sort_order") price_sort_order: String,
    ): Response<ProductList>

    @GET("products/author/search")
    suspend fun getSearchAuthorProducts(
        @Query("author_id") authorId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("products/{product_id}")
    suspend fun getProductInfo(@Path("product_id") product_id: Int): Response<ProductInfoList>

    @GET("products/author")
    suspend fun getProductsByAuthor(
        @Query("author_id") author_id: Int,
        @Query("limit") limit: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductsByAuthor>

    @GET("author/{authorId}")
    suspend fun getAuthor(@Path("authorId") authorId: Int): Response<AuthorResult>

    @GET("products/new")
    suspend fun getSearchNewProduct(): Response<ProductNewList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("customers")
    suspend fun getCustomer(): Response<Customer>

    @FormUrlEncoded
    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @PUT("customers")
    suspend fun updateCustomer(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("date_of_birth") dateofbirth: String,
        @Field("gender") gender: String,
        @Field("mob_phone") mobphone: String,
    ): Response<Customer>

    @FormUrlEncoded
    @POST("customers/changePass")
    suspend fun changePassword(
        @Field("email") email: String,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String,
    ): Response<Customer>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("orders")
    suspend fun getOrderHistory(): Response<OrderList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): Response<OrderDetail>
}