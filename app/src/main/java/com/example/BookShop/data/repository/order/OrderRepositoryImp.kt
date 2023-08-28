package com.example.BookShop.data.repository.order

import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.OrderDetail
import com.example.BookShop.data.model.OrderList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class OrderRepositoryImp(private val dataSource: IDataSource) : OrderRepository {
    override suspend fun getOrderHistory(): Response<OrderList>? {
        return dataSource.getOrderHistory()
    }

    override suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>? {
        return dataSource.getOrderDetail(orderId)
    }

    override suspend fun createOrder(
        cartId: String,
        shippingId: Int,
        address: String,
        receiverName: String,
        receiverPhone: String,
    ): Response<Message> {
        return dataSource.createOrder(
            cartId,
            shippingId,
            address,
            receiverName,
            receiverPhone
        )
    }
}