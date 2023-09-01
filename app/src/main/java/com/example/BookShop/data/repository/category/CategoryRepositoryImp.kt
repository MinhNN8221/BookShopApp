package com.example.BookShop.data.repository.category

import com.example.BookShop.data.model.CategoryList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class CategoryRepositoryImp(private val dataSource: IDataSource) : CategoryRepository {
    override suspend fun getAllCategory(): Response<CategoryList>? {
        return dataSource.getAllCategory()
    }

    override suspend fun getHotCategory(): Response<CategoryList>? {
        return dataSource.getHotCategory()
    }
}