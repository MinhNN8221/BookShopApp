package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("customer") var customer: Customer,
    @SerializedName("expires_in") var expiresIn: String,
)
