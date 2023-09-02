package com.example.BookShop.ui.main.wishlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.ErrorResponse
import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.WishlistResponse
import com.example.BookShop.data.repository.cart.CartRepository
import com.example.BookShop.data.repository.cart.CartRepositoryImp
import com.example.BookShop.data.repository.category.CategoryRepository
import com.example.BookShop.data.repository.wishlist.WishListRepository
import com.example.BookShop.data.repository.wishlist.WishListRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishlistViewModel : ViewModel() {
    private val _wishList = MutableLiveData<WishlistResponse>()
    val wishList: LiveData<WishlistResponse> get() = _wishList
    private val _message = MutableLiveData<Message>()
    val message: LiveData<Message> get() = _message
    private val wishListRepository: WishListRepository = WishListRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())

    fun getWishList(limit: Int, page: Int, description_length: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = wishListRepository.getWishList(limit, page, description_length)
            if (response?.isSuccessful == true) {
                _wishList.postValue(response.body())
            } else {
                Log.d("GETWISHLIST", "NULL")
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
    fun addAllItemToCart(){
        viewModelScope.launch (Dispatchers.IO){
            val response=cartRepository?.addAllItemToCart()
            if (response?.isSuccessful==true){
                _message.postValue(response.body())
            }else{
                val erroBody=response?.errorBody()?.string()
                val gson=Gson()
                val erroResponse=gson.fromJson(erroBody, ErrorResponse::class.java)
                _message.postValue(Message(erroResponse.error.message))
            }
        }
    }
}