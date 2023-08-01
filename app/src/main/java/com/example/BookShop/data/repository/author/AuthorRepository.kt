package com.example.BookShop.data.repository.author

import com.example.BookShop.data.model.Author
import com.example.BookShop.data.model.AuthorResult
import retrofit2.Response

interface AuthorRepository {
    suspend fun getAuthor(authorId: Int): Response<AuthorResult>?
}