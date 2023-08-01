package com.example.BookShop.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.Product
import com.example.BookShop.data.model.ProductNew
import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.repository.search.SearchRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.*

class SearchViewModel() : ViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val _productNewList = MutableLiveData<List<ProductNew>>()
    val productNewList: LiveData<List<ProductNew>> get() = _productNewList

    //    val productList:MutableList<Product> get() = _productList
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    fun getAllProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchProducts(10, 1, 100, "the gioi", 1, "asc")
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
//                Log.d("SEARCH PRODUCT", response.body()?.products.toString())
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }

    fun getSearchNewProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchNewProduct()
            if (response?.isSuccessful == true) {
                _productNewList.postValue(response.body()?.productsNew)
                Log.d("PRODUCTLIST", response.body()?.productsNew.toString())
            } else {
                Log.d("NULL", "NULL")
            }
        }
    }
}