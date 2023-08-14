package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("cart_id")
    val cartId: String,
    @SerializedName("products")
    val cartItem: List<CartItem>,
)
