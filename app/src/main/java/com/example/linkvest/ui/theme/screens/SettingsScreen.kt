package com.example.linkvest.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment

@Composable
fun SettingsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Screen")
    }
}