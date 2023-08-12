package com.example.BookShop.data.repository.auth

import com.example.BookShop.data.model.LoginResponse
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class AuthRepositoryImp(private val iDataSource: IDataSource) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse>? {
        return iDataSource.login(email, password)
    }
}