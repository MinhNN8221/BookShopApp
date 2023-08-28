package com.example.BookShop.data.repository.order

import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.OrderDetail
import com.example.BookShop.data.model.OrderList
import retrofit2.Response


interface OrderRepository {
    suspend fun getOrderHistory(): Response<OrderList>?
    suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>?
    suspend fun createOrder(
        cartId: String,
        shippingId: Int,
        address: String,
        receiverName: String,
        receiverPhone: String,
    ): Response<Message>
}