package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("status") var status: Int = 0,
    @SerializedName("code") var code: String = "",
    @SerializedName("message") var message: String = "",
    @SerializedName("field") var field: String = "",
)
