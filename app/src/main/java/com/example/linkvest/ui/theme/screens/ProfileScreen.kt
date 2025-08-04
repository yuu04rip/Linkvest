package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.linkvest.R

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}

@Composable
fun ProfileScreen(navController: NavController) {
    val topGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFD27B48), Color(0xFFB86F2A))
    )
    val bgGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1A232C), Color(0xFF222836))
    )
    val cardGradient = Brush.horizontalGradient(
        listOf(Color(0xFFD27B48), Color(0xFFB86F2A))
    )
    val textColor = Color(0xFFF6F3E7)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Top bar with back arrow
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(topGradient, RoundedCornerShape(bottomEnd = 60.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFEFC7A7),
                    modifier = Modifier
                        .padding(22.dp)
                        .size(36.dp)
                        .clickable { navController.popBackStack() }
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 28.dp, bottom = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF2C2827)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            tint = Color(0xFFB9C4E1),
                            modifier = Modifier.size(64.dp)
                        )
                    }
                    Spacer(Modifier.width(18.dp))
                    Text(
                        "User",
                        fontSize = 38.sp,
                        color = textColor,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                        modifier = Modifier.offset(y = 10.dp)
                    )
                }
            }

            Spacer(Modifier.height(40.dp))

            // Settings Button
            ProfileCardButton(
                label = "SETTINGS",
                brush = cardGradient,
                width = 320.dp,
                height = 74.dp,
                iconContent = null
            ) { navController.navigate("settings") }

            Spacer(Modifier.height(28.dp))

            // Notifica e Wallet
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileCardButton(
                    label = "",
                    brush = cardGradient,
                    width = 150.dp,
                    height = 130.dp,
                    iconContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = "Notifiche",
                            tint = Color.White,
                            modifier = Modifier.size(42.dp)
                        )
                    }
                ) { /* Notifiche */ }
                ProfileCardButton(
                    label = "",
                    brush = cardGradient,
                    width = 150.dp,
                    height = 130.dp,
                    iconContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.wallet),
                            contentDescription = "Wallet",
                            tint = Color.White,
                            modifier = Modifier.size(42.dp)
                        )
                    }
                ) { /* Wallet */ }
            }

            Spacer(Modifier.height(28.dp))

            // Privacy Button
            ProfileCardButton(
                label = "PRIVACY",
                brush = cardGradient,
                width = 320.dp,
                height = 74.dp,
                iconContent = null
            ) { navController.navigate("privacy") }

            Spacer(Modifier.height(22.dp))

            // Account Button
            ProfileCardButton(
                label = "ACCOUNT",
                brush = cardGradient,
                width = 320.dp,
                height = 74.dp,
                iconContent = null
            ) { navController.navigate("account") }
        }
    }
}

@Composable
fun ProfileCardButton(
    label: String,
    brush: Brush,
    width: Dp,
    height: Dp,
    iconContent: (@Composable (() -> Unit))? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(24.dp))
            .background(brush)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (iconContent == null) {
            Text(
                label,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Light,
                letterSpacing = 2.sp
            )
        } else {
            iconContent()
        }
    }
}