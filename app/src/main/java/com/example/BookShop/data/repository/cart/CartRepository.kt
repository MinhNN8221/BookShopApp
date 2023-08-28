package com.example.BookShop.data.repository.cart

import com.example.BookShop.data.model.Cart
import com.example.BookShop.data.model.CartItem
import com.example.BookShop.data.model.Message
import retrofit2.Response

interface CartRepository {
    suspend fun addCartItem(productId: Int): Response<List<CartItem>>?
    suspend fun getAllCart(): Response<Cart>?
    suspend fun addAllItemToCart(): Response<Message>
    suspend fun deleteAllItemCart(): Response<Message>
    suspend fun changeProductQuantityInCart(itemId: Int, quantity: Int): Response<Message>?
    suspend fun removeItemInCart(itemId: Int): Response<Message>?
}