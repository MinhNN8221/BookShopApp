package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class AuthorResult(
    @SerializedName("result" ) var author : Author,
)