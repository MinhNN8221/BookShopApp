package com.example.BookShop.ui.auth.signin

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

class SignInViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _loginResponse = MutableLiveData<AuthState>()
    val loginResponse: LiveData<AuthState> get() = _loginResponse
    private val authRepository: AuthRepository = AuthRepositoryImp(RemoteDataSource())
    fun checkFields(user: AuthResponse) {

        if (user.isSignInFieldEmpty()) {
            _loginResponse.postValue(
                AuthState(
                    Error(message = "Fields cannot be empty!"),
                    null
                )
            )
            return
        }

        if (!user.isValidEmail()) {
            _loginResponse.postValue(
                AuthState(
                    Error(message = "Please enter a valid email address!"),
                    null
                )
            )
            return
        }

        if (!user.isPasswordGreaterThan4()) {
            _loginResponse.postValue(
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
        checkLogin(user)

    }

    fun checkLogin(user: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.login(user.customer.email, user.customer.password)
            if (response?.isSuccessful == true) {
                _loginResponse.postValue(
                    AuthState(
                        Error(message = "Login Successful!"),
                        response.body()
                    )
                )
            } else {
                val errorBody = response?.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _loginResponse.postValue(
                    AuthState(
                        Error(message = errorResponse.error.message),
                        null
                    )
                )
                Log.d("LoginNull", "NULL")
            }
        }
    }

}