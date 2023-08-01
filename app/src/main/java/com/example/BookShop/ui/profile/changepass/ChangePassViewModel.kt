package com.example.BookShop.ui.profile.changepass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.repository.user.UserRepository
import com.example.BookShop.data.repository.user.UserRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePassViewModel : ViewModel() {
    private val _message= MutableLiveData<String>()
    val message: MutableLiveData<String> get() = _message
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun changePassword(email:String, old_password:String, new_password:String){
        viewModelScope.launch(Dispatchers.IO){
            val response=userRepository?.changePassword(email, old_password, new_password)
            Log.d("RESPONSE", response?.body().toString())
            if(response?.isSuccessful==true){
                message.postValue("UPDATE PASSWORD SUCCESSFUL")
            }else{
                message.postValue("PASSWORD IS INVALID.")
            }
        }
    }
}