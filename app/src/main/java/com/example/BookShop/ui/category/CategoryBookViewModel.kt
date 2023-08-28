package com.example.BookShop.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.Product
import com.example.BookShop.data.model.ProductList
import com.example.BookShop.data.model.ProductState
import com.example.BookShop.data.repository.cart.CartRepository
import com.example.BookShop.data.repository.cart.CartRepositoryImp
import com.example.BookShop.data.repository.product.ProductRepository
import com.example.BookShop.data.repository.product.ProductRepositoryImp
import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.repository.search.SearchRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryBookViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _productList = MutableLiveData<List<Product>>()
    val producList: LiveData<List<Product>> get() = _productList
    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    fun getProductsInCategory(categoryId: Int, limit: Int, page: Int, desLength: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                productRepository?.getProductsByCategory(categoryId, limit, page, desLength)
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("CategroBookNULL", "NULL")
            }
        }
    }

    fun addItemToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.addCartItem(productId)
            if (response?.isSuccessful == true) {
            } else {
                Log.d("ADDITEMTOCARTNULL", "NULL")
            }
        }
    }

    fun getSearchCategoryProducts(categoryId: Int, currentPage: Int, queryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchCategoryProducts(
                10,
                currentPage,
                100,
                queryString,
                categoryId,
            )
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("SearchCategory", "NULL")
            }
        }
    }
}