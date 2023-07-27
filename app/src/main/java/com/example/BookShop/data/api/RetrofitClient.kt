package com.example.shopbook.api

import com.example.BookShop.data.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://424f-116-97-243-66.ngrok-free.app/"
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(): ApiInterface? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(ApiInterface::class.java)
    }
}