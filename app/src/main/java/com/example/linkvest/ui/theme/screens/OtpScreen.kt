package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.linkvest.auth.ApiClient
import com.example.linkvest.auth.OtpRequest
import com.example.linkvest.auth.ResendOtpRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun OtpScreenPreview() {
    OtpScreen(email = "test@email.com", navController = rememberNavController())
}

@Composable
fun OtpScreen(email: String, navController: NavController) {
    var otp by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    // Timer state
    var resendTimer by remember { mutableStateOf(20) }
    var canResend by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Timer effect
    LaunchedEffect(resendTimer) {
        if (resendTimer > 0) {
            canResend = false
            delay(1000)
            resendTimer -= 1
        } else {
            canResend = true
        }
    }

    // Palette
    val backgroundColor = Color(0xFFD9A48F)
    val cardColor = Color(0xFFF3D7C6)
    val accentColor = Color(0xFF29435C)
    val placeholderColor = Color(0xFFD9A48F)
    val textColor = Color(0xFFF6F3E7)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(56.dp))
            Text(
                text = "Check your email!",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "WE SENT YOU AN OTP CODE",
                fontSize = 16.sp,
                color = textColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 36.dp)
            )

            // OTP Field
            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                leadingIcon = { Icon(Icons.Default.Key, contentDescription = "OTP", tint = placeholderColor) },
                placeholder = { Text("OTP CODE", color = placeholderColor) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(cardColor, RoundedCornerShape(24.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = cardColor,
                    unfocusedContainerColor = cardColor,
                    disabledContainerColor = cardColor,
                    focusedTextColor = placeholderColor,
                    unfocusedTextColor = placeholderColor,
                    disabledTextColor = placeholderColor,
                    focusedPlaceholderColor = placeholderColor,
                    unfocusedPlaceholderColor = placeholderColor,
                    disabledPlaceholderColor = placeholderColor,
                    focusedLeadingIconColor = placeholderColor,
                    unfocusedLeadingIconColor = placeholderColor,
                    disabledLeadingIconColor = placeholderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(24.dp)
            )

            Spacer(Modifier.height(32.dp))

            // Verifica Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val response = ApiClient.otpApi.verifyOtp(OtpRequest(email, otp))
                            if (response.success) {
                                navController.navigate("home") {
                                    popUpTo(0) { inclusive = true } // opzionale: rimuove lo screen OTP dallo stack
                                }
                            } else {
                                errorMessage = response.message
                            }
                        } catch (e: Exception) {
                            errorMessage = e.localizedMessage ?: "Errore di rete"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Verify", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = textColor)
            }

            if (successMessage != null) {
                Spacer(Modifier.height(20.dp))
                Text(successMessage!!, color = Color(0xFF388E3C), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            if (errorMessage != null) {
                Spacer(Modifier.height(20.dp))
                Text(errorMessage!!, color = Color.Red, fontSize = 16.sp)
            }

            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Didn't receive the code?",
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = if (canResend) Modifier.clickable {
                        coroutineScope.launch {
                            try {
                                val response = ApiClient.otpApi.resendOtp(ResendOtpRequest(email))
                                if (response.success) {
                                    successMessage = response.message ?: "OTP inviato!"
                                    errorMessage = null
                                    resendTimer = 20 // riparte il timer
                                } else {
                                    errorMessage = response.message ?: "Errore durante l'invio OTP"
                                    successMessage = null
                                }
                            } catch (e: Exception) {
                                errorMessage = e.localizedMessage ?: "Errore di rete"
                                successMessage = null
                            }
                        }
                    } else Modifier
                ) {
                    Text(
                        if (canResend) "RESEND"
                        else "RESEND (${resendTimer}s)",
                        color = if (canResend) textColor else textColor.copy(alpha = 0.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}