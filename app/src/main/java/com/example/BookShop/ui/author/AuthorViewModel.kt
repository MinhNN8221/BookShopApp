package com.example.BookShop.ui.author

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.AuthorInfor
import com.example.BookShop.data.model.Product
import com.example.BookShop.data.model.ProductState
import com.example.BookShop.data.repository.author.AuthorRepository
import com.example.BookShop.data.repository.author.AuthorRepositoryImp
import com.example.BookShop.data.repository.cart.CartRepository
import com.example.BookShop.data.repository.cart.CartRepositoryImp
import com.example.BookShop.data.repository.product.ProductRepository
import com.example.BookShop.data.repository.product.ProductRepositoryImp
import com.example.BookShop.data.repository.search.SearchRepository
import com.example.BookShop.data.repository.search.SearchRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthorViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _productState = MutableLiveData<ProductState>()
    val productState: LiveData<ProductState> get() = _productState
    private val _author = MutableLiveData<AuthorInfor>()
    val author: LiveData<AuthorInfor> get() = _author

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    private var authorRepository: AuthorRepository? = AuthorRepositoryImp(RemoteDataSource())
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    var job: Job? = null
    fun getProductsByAuthor(authorId: Int, limit: Int, page: Int, desLength: Int) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = productRepository?.getProductsByAuthor(authorId, limit, page, desLength)
            if (response?.isSuccessful == true) {
                _productState.postValue(ProductState(response.body()?.products, true))
            } else {
                Log.d("getProductsInAuthor", "NULLLL")
            }
        }
    }

    fun getSearchAuthorProduct(authorId: Int, currentPage: Int, queryString: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val response =
                searchRepository?.getSearchAuthorProducts(
                    authorId,
                    10,
                    currentPage,
                    100,
                    queryString
                )
            if (response?.isSuccessful == true) {
                _productState.postValue(ProductState(response.body()?.products, false))
            } else {
                Log.d("searchAuthor", "NULLLL")
            }
        }
    }

    fun getAuthor(authorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authorRepository?.getAuthor(authorId)
            if (response?.isSuccessful == true) {
                _author.postValue(response.body())
            } else {
                Log.d("AUTHORNULL", "NULL")
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
}