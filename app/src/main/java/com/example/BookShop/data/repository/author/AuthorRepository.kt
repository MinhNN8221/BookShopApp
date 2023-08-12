package com.example.BookShop.data.repository.author

import com.example.BookShop.data.model.AuthorList
import com.example.BookShop.data.model.AuthorResult
import retrofit2.Response

interface AuthorRepository {
    suspend fun getAllAuthors() : Response<AuthorList>?
    suspend fun getAuthor(authorId: Int): Response<AuthorResult>?
}