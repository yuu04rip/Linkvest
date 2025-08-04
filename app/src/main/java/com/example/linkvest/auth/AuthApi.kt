package com.example.linkvest.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}