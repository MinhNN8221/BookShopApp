package com.example.BookShop.data.model
import com.google.gson.annotations.SerializedName

data class Wishlist(
    @SerializedName("product_id")
    val product_id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("discounted_price")
    val discount: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
)