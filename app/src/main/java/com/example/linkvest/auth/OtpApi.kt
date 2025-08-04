package com.example.linkvest.auth

import retrofit2.http.Body
import retrofit2.http.POST

data class OtpRequest(val email: String, val otp: String)
data class OtpResponse(val success: Boolean, val message: String?)

data class ResendOtpRequest(val email: String)
data class ResendOtpResponse(val success: Boolean, val message: String?)
interface OtpApi {
    @POST("/api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: OtpRequest): OtpResponse

    @POST("/api/auth/resend-otp")
    suspend fun resendOtp(@Body request: ResendOtpRequest): ResendOtpResponse
}