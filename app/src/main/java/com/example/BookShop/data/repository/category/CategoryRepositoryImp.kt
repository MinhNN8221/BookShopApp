package com.example.BookShop.data.repository.category

import com.example.BookShop.data.model.CategoryList
import com.example.BookShop.datasource.IDataSource
import retrofit2.Response

class CategoryRepositoryImp(private val dataSource: IDataSource) : CategoryRepository{
    override suspend fun getCategory(): Response<CategoryList>? {
        return dataSource.getCategory()
    }
}