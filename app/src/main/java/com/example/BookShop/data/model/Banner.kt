package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("product_id") var productId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("discounted_price") var discountedPrice: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("banner_url") var bannerUrl: String? = null,
)
