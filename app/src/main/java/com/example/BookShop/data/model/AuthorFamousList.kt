package com.example.BookShop.data.model

import com.google.gson.annotations.SerializedName

data class AuthorFamousList(
    @SerializedName("authors") var authors: List<Author>,
)
