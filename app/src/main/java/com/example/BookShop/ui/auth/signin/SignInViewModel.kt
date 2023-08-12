package com.example.BookShop.ui.auth.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.LoginResponse
import com.example.BookShop.data.model.LoginState
import com.example.BookShop.data.repository.auth.AuthRepository
import com.example.BookShop.data.repository.auth.AuthRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _loginResponse = MutableLiveData<LoginState>()
    val loginResponse: MutableLiveData<LoginState> get() = _loginResponse
    private val authRepository: AuthRepository = AuthRepositoryImp(RemoteDataSource())
    fun checkLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.login(email, password)
            if (response?.isSuccessful == true) {
                _loginResponse.postValue(LoginState(true, response.body()))
            } else {
                _loginResponse.postValue(LoginState(false, null))
                Log.d("LoginNull", "NULL")
            }
        }
    }
}