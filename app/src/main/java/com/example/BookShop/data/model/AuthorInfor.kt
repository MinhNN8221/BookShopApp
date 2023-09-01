package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class AuthorInfor(
    @SerializedName("result" ) var author : Author,
)