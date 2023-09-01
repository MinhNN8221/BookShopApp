package com.example.BookShop.data.repository.author

import com.example.BookShop.data.model.AuthorFamousList
import com.example.BookShop.data.model.AuthorInfor
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class AuthorRepositoryImp(private val iDataSource: IDataSource) : AuthorRepository {
    override suspend fun getHotAuthors(): Response<AuthorFamousList>? {
        return iDataSource.getHotAuthor()
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorInfor>? {
        return iDataSource.getAuthor(authorId)
    }
}