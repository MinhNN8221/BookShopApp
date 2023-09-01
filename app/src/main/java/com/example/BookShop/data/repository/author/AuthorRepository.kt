package com.example.BookShop.data.repository.author

import com.example.BookShop.data.model.AuthorFamousList
import com.example.BookShop.data.model.AuthorInfor
import retrofit2.Response

interface AuthorRepository {
    suspend fun getHotAuthors() : Response<AuthorFamousList>?
    suspend fun getAuthor(authorId: Int): Response<AuthorInfor>?
}