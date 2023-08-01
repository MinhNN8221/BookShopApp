package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class OrderHistory(
    val header: String?,
    val order: Order?,
)