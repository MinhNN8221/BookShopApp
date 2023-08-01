package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class ProductNewList(
    @SerializedName("count") var count: Int,
    @SerializedName("products") var productsNew: List<ProductNew>,
)
