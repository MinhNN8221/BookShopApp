package com.example.BookShop.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.Customer
import com.example.BookShop.data.repository.user.UserRepository
import com.example.BookShop.data.repository.user.UserRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profile = MutableLiveData<Customer>()
    val profile: LiveData<Customer> get() = _profile
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun getCustomer() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.getCustomer()
            if (response?.isSuccessful == true) {
                _profile.postValue(response.body())
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }
}