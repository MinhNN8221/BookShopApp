package com.example.BookShop.data.repository.user

import com.example.BookShop.data.model.Customer
import okhttp3.MultipartBody
import retrofit2.Response

interface UserRepository {
    suspend fun getCustomer(): Response<Customer>?
    suspend fun updateCustomer(
        name: String,
        address: String,
        dob: String,
        gender: String,
        mob_phone: String,
    ): Response<Customer>?
    suspend fun updateOrderInfor(
        name: String,
        address: String,
        mob_phone: String,
    ): Response<Customer>?

    suspend fun changePassword(
        email: String, old_password: String,
        new_password: String,
    ): Response<Customer>?

    suspend fun changeAvatar(image: MultipartBody.Part): Response<Customer>?
}