package com.example.BookShop.data.repository.wishlist

import com.example.BookShop.data.model.Messeage
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class WishListRepositoryImp(private val dataSource: IDataSource) : WishListRepository {
    override suspend fun addItemToWishList(productId: Int): Response<Messeage>? {
        return dataSource.addItemToWishList(productId)
    }

    override suspend fun removeItemInWishList(productId: Int): Response<Messeage>? {
        return dataSource.removeItemInWishList(productId)
    }
}