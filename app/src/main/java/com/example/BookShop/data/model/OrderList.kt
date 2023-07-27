package com.example.BookShop.data.model

import com.example.BookShop.data.model.Order
import com.google.gson.annotations.SerializedName

data class OrderList(
    @SerializedName("count") var count: Int?,
    @SerializedName("orders") var orders: List<Order>,
)
