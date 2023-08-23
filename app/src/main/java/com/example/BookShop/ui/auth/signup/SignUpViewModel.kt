package com.example.BookShop.ui.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.AuthState
import com.example.BookShop.data.model.Error
import com.example.BookShop.data.model.ErrorResponse
import com.example.BookShop.data.repository.auth.AuthRepository
import com.example.BookShop.data.repository.auth.AuthRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private var _registerResponse = MutableLiveData<AuthState>()
    val registerResponse: LiveData<AuthState> get() = _registerResponse
    private val authRepository: AuthRepository = AuthRepositoryImp(RemoteDataSource())

    fun checkFields(user: AuthResponse) {
        if (user.isSignUpFieldEmpty()) {
            _registerResponse.postValue(
                AuthState(
                    Error(message = "Fields cannot be empty!"),
                    null
                )
            )
            return
        }
        if (!user.isValidEmail()) {
            _registerResponse.postValue(
                AuthState(
                    Error(message = "Please enter a valid email address!"),
                    null
                )
            )
            return
        }
        if (!user.isPasswordGreaterThan4()) {
            _registerResponse.postValue(
                AuthState(
                    Error(
                        message =
                        "Password must be greater than 4 characters!"
                    ),
                    null
                )
            )
            return
        }
        if (!user.isPasswordMatch(user.customer.password)) {
            _registerResponse.postValue(AuthState(Error(message = "Passwords don't match!"), null))
            return
        }
        signUp(user)
    }

    private fun signUp(user: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.register(
                user.customer.email,
                user.customer.name,
                user.customer.password
            )
            if (response.isSuccessful == true) {
                _registerResponse.postValue(
                    AuthState(
                        Error(message = "Register Successful!"),
                        response.body()
                    )
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _registerResponse.postValue(
                    AuthState(
                        Error(message = errorResponse.error.message),
                        null
                    )
                )
                Log.d("RegisterNull", "NULL")
            }
        }
    }
}