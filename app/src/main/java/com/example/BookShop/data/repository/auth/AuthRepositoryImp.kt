package com.example.BookShop.data.repository.auth

import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.Message
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class AuthRepositoryImp(private val iDataSource: IDataSource) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<AuthResponse>? {
        return iDataSource.login(email, password)
    }

    override suspend fun forgotPassword(email: String): Response<Message> {
        return iDataSource.forgotPassword(email)
    }

    override suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Response<AuthResponse> {
        return iDataSource.register(email, name, password)
    }
}