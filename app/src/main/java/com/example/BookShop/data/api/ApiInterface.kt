package com.example.BookShop.data.api

import com.example.BookShop.data.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("customers/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(): Response<ProductList>

    @GET("category")
    suspend fun getCategory(): Response<CategoryList>

    @GET("author/hot")
    suspend fun getAuthor(): Response<AuthorList>

    @GET("products/search")
    suspend fun getSearchProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
        @Query("filter_type") filterType: Int,
        @Query("price_sort_order") priceSortOrder: String,
    ): Response<ProductList>

    @GET("products/search")
    suspend fun getSearchHistory(
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @GET("products/author/search")
    suspend fun getSearchAuthorProducts(
        @Query("author_id") authorId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @GET("products/category/search")
    suspend fun getSearchCategoryProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
        @Query("category_id") categoryId: Int,
    ): Response<ProductList>

    @GET("products/{product_id}")
    suspend fun getProductInfo(@Path("product_id") product_id: Int): Response<ProductInfoList>

    @GET("products/author")
    suspend fun getProductsByAuthor(
        @Query("author_id") author_id: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductsByAuthor>

    @GET("products/incategory/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductList>

    @GET("author/{authorId}")
    suspend fun getAuthor(@Path("authorId") authorId: Int): Response<AuthorResult>

    @GET("products/new")
    suspend fun getSearchNewProduct(): Response<ProductNewList>

    @GET("customers")
    suspend fun getCustomer(): Response<Customer>

    @FormUrlEncoded
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

    @Multipart

    @POST("customers/update/avatar")
    suspend fun changeAvatar(
        @Part image: MultipartBody.Part,
    ): Response<Customer>


    @GET("orders")
    suspend fun getOrderHistory(): Response<OrderList>


    @GET("orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): Response<OrderDetail>

    @FormUrlEncoded

    @POST("shoppingCart/add")
    suspend fun addProduct2Cart(@Field("product_id") productId: Int): Response<List<CartItem>>

    @FormUrlEncoded
    @POST("wishlist/add")
    suspend fun addItemToWishList(@Field("product_id") productId: Int): Response<Messeage>


    @DELETE("wishlist/remove/{product_id}")
    suspend fun removeItemInWishList(@Path("product_id") productId: Int): Response<Messeage>

    @GET("wishlist")
    suspend fun getWishList(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
    ): Response<WishlistResponse>?

    @GET("shoppingCart")
    suspend fun getAllCart(): Response<Cart>?
}