package com.example.BookShop.data.model

import com.example.BookShop.data.model.Author
import com.google.gson.annotations.SerializedName

data class AuthorList(
    @SerializedName("authors") var authors: List<Author>,
)
