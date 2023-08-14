package com.example.BookShop.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://c9c4-113-190-246-18.ngrok-free.app/"
    private var accessToken=""

    fun updateAccessToken(token: String) {
        accessToken = token
    }
    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("user-key", accessToken)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })

        val client = httpClient.build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}