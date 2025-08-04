package com.example.linkvest.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DrawerMenu(navController: NavController) {
    val backgroundColor = Color(0xFFD27B48)
    val textColor = Color(0xFFF6F3E7)
    val separatorColor = Color(0xFF6E4B3A)

    // Usa il 65% della larghezza dello schermo
    val drawerWidth = (LocalConfiguration.current.screenWidthDp.dp * 0.65f)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(drawerWidth)
            .background(backgroundColor, RoundedCornerShape(32.dp))
            .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 24.dp)
    ) {
        // Avatar & Ciao
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2C2827))
                    .clickable{ navController.navigate("profile") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color(0xFFB9C4E1),
                    modifier = Modifier.size(54.dp)
                )
            }
            Spacer(Modifier.width(14.dp))
            Text(
                "Hi User!",
                fontSize = 32.sp,
                color = textColor,
                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Spacer(Modifier.height(44.dp))

        DrawerMenuItem("SETTINGS", separatorColor, textColor) {
            navController.navigate("settings")
        }
        DrawerMenuItem("FAVOURITE", separatorColor, textColor) {
            navController.navigate("favourite")
        }
        DrawerMenuItem("CHAT", separatorColor, textColor) {
            navController.navigate("chat")
        }
        DrawerMenuItem("HELP", separatorColor, textColor) {
            navController.navigate("help")
        }
    }
}

@Composable
private fun DrawerMenuItem(
    text: String,
    separatorColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 26.sp,
            color = textColor,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(separatorColor)
        )
    }
}