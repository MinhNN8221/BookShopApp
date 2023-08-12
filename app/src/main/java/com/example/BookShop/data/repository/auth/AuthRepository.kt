package com.example.BookShop.data.repository.auth

import com.example.BookShop.data.model.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>?
}