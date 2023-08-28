package com.example.BookShop.data.repository.wishlist

import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.WishlistResponse
import retrofit2.Response

interface WishListRepository {
    suspend fun addItemToWishList(productId: Int): Response<Message>?
    suspend fun removeItemInWishList(productId: Int): Response<Message>?

    suspend fun getWishList(
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<WishlistResponse>?
}