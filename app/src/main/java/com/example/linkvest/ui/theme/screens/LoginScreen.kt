package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Colors (personalizza a piacere)
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

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email", tint = placeholderColor) },
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
            // Password field
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 42.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    "FORGOT PASSWORD?",
                    fontSize = 14.sp,
                    color = textColor.copy(alpha = 0.7f),
                    modifier = Modifier.clickable {
                        // TODO: forgot password action
                    }
                )
            }

            // Login button
            Button(
                onClick = { /* TODO: handle login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Log in", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = textColor)
            }

            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "DON'T HAVE AN ACCOUNT?",
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "SIGN UP",
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .clickable { navController.navigate("register") }
                )
            }
        }
    }
}