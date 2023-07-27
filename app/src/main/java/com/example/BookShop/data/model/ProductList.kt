package com.example.BookShop.data.model

import com.example.BookShop.data.model.Product
import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("rows")
    var products: List<Product>,
)