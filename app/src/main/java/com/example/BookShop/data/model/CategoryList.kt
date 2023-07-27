package com.example.BookShop.data.model

import com.example.BookShop.data.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("count") var count: Int?,
    @SerializedName("rows")
    var categories: List<Category>,
)
