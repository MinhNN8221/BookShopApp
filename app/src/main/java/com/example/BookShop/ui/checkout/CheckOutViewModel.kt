package com.example.BookShop.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.ErrorResponse
import com.example.BookShop.data.model.Message
import com.example.BookShop.data.model.MessageState
import com.example.BookShop.data.repository.order.OrderRepository
import com.example.BookShop.data.repository.order.OrderRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckOutViewModel : ViewModel() {
    private var _message = MutableLiveData<MessageState>()
    val message: LiveData<MessageState> get() = _message
    private val orderRepository: OrderRepository = OrderRepositoryImp(RemoteDataSource())

    fun createOrder(
        cartId: String,
        shippingId: Int,
        address: String,
        receiverName: String,
        recieverPhone: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                orderRepository.createOrder(
                    cartId,
                    shippingId,
                    address,
                    receiverName,
                    recieverPhone
                )
            if (response.isSuccessful == true) {
                _message.postValue(response.body()?.let { MessageState(true, it) })
            } else {
                val errorBody = response.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _message.postValue(MessageState(false, Message(errorResponse.error.message)))
            }
        }
    }
}