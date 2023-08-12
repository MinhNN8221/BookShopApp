package com.example.BookShop.data.repository.cart

import com.example.BookShop.data.model.CartItem
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class CartRepositoryImp(private val dataSource: IDataSource) : CartRepository {
    override suspend fun addCartItem(productId: Int): Response<List<CartItem>>? {
        return dataSource.addCartItem(productId)
    }
}