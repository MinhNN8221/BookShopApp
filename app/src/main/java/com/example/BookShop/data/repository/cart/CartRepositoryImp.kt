package com.example.BookShop.data.repository.cart

import com.example.BookShop.data.model.Cart
import com.example.BookShop.data.model.CartItem
import com.example.BookShop.data.model.Message
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class CartRepositoryImp(private val dataSource: IDataSource) : CartRepository {
    override suspend fun addCartItem(productId: Int): Response<List<CartItem>>? {
        return dataSource.addCartItem(productId)
    }

    override suspend fun getAllCart(): Response<Cart>? {
        return dataSource.getAllCart()
    }

    override suspend fun addAllItemToCart(): Response<Message> {
        return dataSource.addAllItemToCart()
    }

    override suspend fun deleteAllItemCart(): Response<Message> {
        return dataSource.deleteAllItemCart()
    }

    override suspend fun changeProductQuantityInCart(
        itemId: Int,
        quantity: Int,
    ): Response<Message>? {
        return dataSource.changeProductQuantityInCart(itemId, quantity)
    }

    override suspend fun removeItemInCart(itemId: Int): Response<Message>? {
        return dataSource.removeItemInCart(itemId)
    }
}