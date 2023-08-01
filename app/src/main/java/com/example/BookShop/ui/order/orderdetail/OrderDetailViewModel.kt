package com.example.BookShop.ui.order.orderdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.OrderDetail
import com.example.BookShop.data.model.OrderDetailProduct
import com.example.BookShop.data.repository.order.OrderRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _orderDetailList = MutableLiveData<OrderDetail>()
    val orderDetailList: MutableLiveData<OrderDetail> get() = _orderDetailList
    private var orderRepository: OrderRepositoryImp =OrderRepositoryImp(RemoteDataSource())

    fun getOrderDetails(orderId:Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response=orderRepository.getOrderDetail(orderId)
            if(response?.isSuccessful==true){
                _orderDetailList.postValue(response.body())
            }else{
                Log.d("OrderDetailNULL", "NULL")
            }
        }
    }
}