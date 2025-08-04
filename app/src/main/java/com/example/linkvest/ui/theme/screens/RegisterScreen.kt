package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.linkvest.auth.ApiClient
import com.example.linkvest.auth.RegisterRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("imprenditore") }
    var referralCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val backgroundColor = Color(0xFFD9A48F)
    val cardColor = Color(0xFFF3D7C6)
    val accentColor = Color(0xFF29435C)
    val placeholderColor = Color(0xFFD9A48F)
    val textColor = Color(0xFFF6F3E7)

    val roleOptions = listOf("imprenditore", "investitore", "admin")
    var expanded by remember { mutableStateOf(false) }

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
                text = "We say hello!",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "WELCOME TO YOUR EMAIL AND PASSWORD LOGIN",
                fontSize = 16.sp,
                color = textColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                leadingIcon = { Icon(Icons.Default.AlternateEmail, contentDescription = "Email", tint = placeholderColor) },
                placeholder = { Text("EMAIL", color = placeholderColor) },
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
                    focusedTrailingIconColor = placeholderColor,
                    unfocusedTrailingIconColor = placeholderColor,
                    disabledTrailingIconColor = placeholderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(Modifier.height(18.dp))

            // Role dropdown
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(cardColor, RoundedCornerShape(24.dp))
                    .clickable { expanded = true }
            ) {
                Text(
                    text = role.uppercase(),
                    color = placeholderColor,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    roleOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.uppercase()) },
                            onClick = {
                                role = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(18.dp))

            // Referral code (opzionale)
            OutlinedTextField(
                value = referralCode,
                onValueChange = { referralCode = it },
                placeholder = { Text("REFERRAL CODE (opzionale)", color = placeholderColor) },
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
                    focusedTrailingIconColor = placeholderColor,
                    unfocusedTrailingIconColor = placeholderColor,
                    disabledTrailingIconColor = placeholderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(24.dp)
            )

            Spacer(Modifier.height(18.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password", tint = placeholderColor) },
                placeholder = { Text("PASSWORD", color = placeholderColor) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
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
                    focusedTrailingIconColor = placeholderColor,
                    unfocusedTrailingIconColor = placeholderColor,
                    disabledTrailingIconColor = placeholderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(24.dp)
            )

            Spacer(Modifier.height(32.dp))

            // Sign Up button
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val response = ApiClient.authApi.register(
                                RegisterRequest(
                                    email = email,
                                    password = password,
                                    role = role,
                                    referralCode = if (referralCode.isBlank()) null else referralCode
                                )
                            )
                            if (response.success) {
                                successMessage = "Registrazione avvenuta con successo!"
                                // Attendi 2 secondi, poi vai alla OTP screen
                                delay(2000)
                                navController.navigate("otp/${email}")
                            } else {
                                errorMessage = response.message ?: "Registration error"
                            }
                        } catch (e: Exception) {
                            errorMessage = e.localizedMessage ?: "Network error"
                        }
                    }
                },
                enabled = successMessage == null, // Disattiva mentre mostra il messaggio di successo
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Sign Up", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = textColor)
            }

            // Success message
            successMessage?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = Color(0xFF388E3C), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            // Error message
            errorMessage?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = Color.Red)
            }

            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "ALREADY HAVE AN ACCOUNT?",
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "SIGN IN",
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .clickable { navController.navigate(route="login") }
                )
            }
        }
    }
}