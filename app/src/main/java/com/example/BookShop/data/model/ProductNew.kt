package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class ProductNew(
    @SerializedName("product_id") var productId: Int,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: String,
    @SerializedName("discounted_price") var discountedPrice: String,
    @SerializedName("thumbnail") var thumbnail: String,
)