package com.example.BookShop.ui.productdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookShop.data.model.Author
import com.example.BookShop.data.model.ProductInfo
import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.data.model.Supplier
import com.example.BookShop.data.repository.product.ProductRepository
import com.example.BookShop.data.repository.product.ProductRepositoryImp
import com.example.BookShop.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductdetailViewModel : ViewModel() {
    private val _productListInfo = MutableLiveData<ProductInfoList?>()
    val productInfo: MutableLiveData<ProductInfoList?> get() = _productListInfo

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    fun getProductInfo(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val response = productRepository?.getProductInfo(id)
            if (response?.isSuccessful == true) {
//                _productInfo.postValue(response.body()?.product)
//                _supplierInfo.postValue(response.body()?.supplier)
//                _authorInfo.postValue(response.body()?.author)
                _productListInfo.postValue(response.body())
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }
}