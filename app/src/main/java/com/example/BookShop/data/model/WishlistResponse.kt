package com.example.BookShop.data.model
import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("count") val count : Int,
    @SerializedName("rows") val wishlist: List<Wishlist>,
)
