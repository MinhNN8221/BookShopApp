package com.example.BookShop.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.Product
import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.repository.search.SearchRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.*

class SearchViewModel() : ViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList

    //    val productList:MutableList<Product> get() = _productList
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    fun getAllProducts() {

        viewModelScope.launch(Dispatchers.IO) {

            val response = searchRepository?.getAllProducts()
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }
}