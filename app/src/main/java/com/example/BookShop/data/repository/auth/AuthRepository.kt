package com.example.BookShop.data.repository.auth

import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.Message
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<AuthResponse>?
    suspend fun forgotPassword(email: String): Response<Message>
    suspend fun register(email: String, name: String, password: String): Response<AuthResponse>
}