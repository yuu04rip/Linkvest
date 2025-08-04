package com.example.linkvest.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://invest-app-backend-k82n.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }
    val otpApi: OtpApi by lazy {
        retrofit.create(OtpApi::class.java)
    }
}