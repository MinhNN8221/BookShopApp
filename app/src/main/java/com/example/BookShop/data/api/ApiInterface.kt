package com.example.BookShop.data.api

import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.ProductList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<ProductList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("products/{product_id}")
    suspend fun getProductInfo(@Path("product_id") product_id: Int): Response<ProductInfoList>
}