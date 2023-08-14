package com.example.BookShop.ui.main.shoppingbag

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.CartItem
import com.example.BookShop.data.repository.cart.CartRepository
import com.example.BookShop.data.repository.cart.CartRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingbagViewModel : ViewModel() {
    private val _cartItem = MutableLiveData<List<CartItem>>()
    val cartItem: LiveData<List<CartItem>> get() = _cartItem
    private val carRepository: CartRepository = CartRepositoryImp(RemoteDataSource())
    fun getAllCartItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = carRepository.getAllCart()
            if (response?.isSuccessful == true) {
                _cartItem.postValue(response.body()?.cartItem)
            } else {
                Log.d("GetAllCart", "NULL")
            }
        }
    }
}