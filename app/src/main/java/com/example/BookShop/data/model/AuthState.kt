package com.example.BookShop.data.model

data class AuthState(
    val error: Error,
    val loginResponse: AuthResponse?,
)
