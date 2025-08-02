package com.example.linkvest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.linkvest.ui.theme.AppTheme
import com.example.linkvest.ui.theme.screens.*
import com.example.linkvest.ui.theme.screens.onboarding.OnboardingScreen// <- Import corretto!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LinkvestApp()
            }
        }
    }
}

@Composable
fun LinkvestApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {

        composable("onboarding") { OnboardingScreen(navController) } // Onboarding come da tuo file
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("drawer") { DrawerMenu(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("albums") { AlbumsScreen(navController) }
        composable("albumDetail") { AlbumDetailScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
    }
}