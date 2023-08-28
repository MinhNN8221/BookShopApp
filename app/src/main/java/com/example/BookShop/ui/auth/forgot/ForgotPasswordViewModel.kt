package com.example.BookShop.ui.auth.forgot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.*
import com.example.BookShop.data.repository.auth.AuthRepository
import com.example.BookShop.data.repository.auth.AuthRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _message = MutableLiveData<Message>()
    val message: LiveData<Message> get() = _message
    private var authRepository: AuthRepository = AuthRepositoryImp(RemoteDataSource())

    fun checkFields(user: AuthResponse) {
        if (user.isForgotPassFieldEmpty()) {
            _message.postValue(Message("Fields cannot be empty!"))
            return
        }

        if (!user.isValidEmail()) {
            _message.postValue(Message("Please enter a valid email address!"))
            return
        }
        forgotPassword(user.customer.email)
    }
    fun forgotPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.forgotPassword(email)
            if (response.isSuccessful==true) {
                _message.postValue(response.body())
            } else {
                val errorBody = response?.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _message.postValue(Message(message = errorResponse.error.message))
                Log.d("ForgotPassNull", "NULL")
            }
        }
    }
}