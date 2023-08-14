package com.example.BookShop.data.repository.cart

import com.example.BookShop.data.model.Cart
import com.example.BookShop.data.model.CartItem
import retrofit2.Response

interface CartRepository {
    suspend fun addCartItem(productId: Int): Response<List<CartItem>>?
    suspend fun getAllCart():Response<Cart>?
}