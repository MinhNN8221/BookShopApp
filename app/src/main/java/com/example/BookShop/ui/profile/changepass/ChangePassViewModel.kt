package com.example.BookShop.ui.profile.changepass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.ErrorResponse
import com.example.BookShop.data.repository.user.UserRepository
import com.example.BookShop.data.repository.user.UserRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePassViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: MutableLiveData<String> get() = _message
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())

    fun checkFields(user: AuthResponse) {
        if (user.isChangePassEmpty()) {
            _message.postValue("Fields cannot be empty!")
            return
        }
        if (!user.isPasswordGreaterThan4()) {
            _message.postValue("Password must be greater than 4 characters!")
        }
        if (!user.isPasswordMatch(user.customer.newPassword)) {
            _message.postValue("Passwords don't match!")
            return
        }
        changePassword(user)
    }

    fun changePassword(user: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.changePassword(user.customer.email, user.customer.password, user.customer.newPassword)
            if (response?.isSuccessful == true) {
                message.postValue("Update Password Successful")
            } else {
                val errorBody=response?.errorBody()?.string()
                val gson=Gson()
                val errorResponse=gson.fromJson(errorBody, ErrorResponse::class.java)
                message.postValue(errorResponse.error.message)
            }
        }
    }
}