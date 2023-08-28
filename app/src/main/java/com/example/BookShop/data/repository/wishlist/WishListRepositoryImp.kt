package com.example.BookShop.data.repository.wishlist

import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.WishlistResponse
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class WishListRepositoryImp(private val dataSource: IDataSource) : WishListRepository {
    override suspend fun addItemToWishList(productId: Int): Response<Message>? {
        return dataSource.addItemToWishList(productId)
    }

    override suspend fun removeItemInWishList(productId: Int): Response<Message>? {
        return dataSource.removeItemInWishList(productId)
    }

    override suspend fun getWishList(
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<WishlistResponse>? {
        return dataSource.getWishList(limit, page, description_length)
    }
}