package com.example.BookShop.data.repository.wishlist

import com.example.BookShop.data.model.Messeage
import retrofit2.Response

interface WishListRepository {
    suspend fun addItemToWishList(productId: Int): Response<Messeage>?
    suspend fun removeItemInWishList(productId: Int): Response<Messeage>?
}