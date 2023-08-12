package com.example.BookShop.data.repository.author

import com.example.BookShop.data.model.Author
import com.example.BookShop.data.model.AuthorList
import com.example.BookShop.data.model.AuthorResult
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class AuthorRepositoryImp(private val iDataSource: IDataSource) : AuthorRepository {
    override suspend fun getAllAuthors(): Response<AuthorList>? {
        return iDataSource.getAllAuthor()
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorResult>? {
        return iDataSource.getAuthor(authorId)
    }
}