package com.example.linkvest.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val role: String,
    val referralCode: String? = null
)

data class RegisterResponse(
    val success: Boolean,
    val message: String?
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String?,
    val token: String? = null
)