package com.example.BookShop.data.model

data class LoginResponse(
    val accessToken: String,
    val customer: Customer,
    val expiresIn: String
)