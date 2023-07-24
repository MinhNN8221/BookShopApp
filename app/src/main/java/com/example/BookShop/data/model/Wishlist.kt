package com.example.BookShop.data.model

data class Wishlist(
    val product_id: Int,
    val name: String,
    val description: String,
    val price: String,
    val discount: String,
    val thumbnail: String,
)