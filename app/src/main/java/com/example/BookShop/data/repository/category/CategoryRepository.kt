package com.example.BookShop.data.repository.category

import com.example.BookShop.data.model.CategoryList
import retrofit2.Response

interface CategoryRepository {
    suspend fun getAllCategory(): Response<CategoryList>?
    suspend fun getHotCategory():Response<CategoryList>?
}