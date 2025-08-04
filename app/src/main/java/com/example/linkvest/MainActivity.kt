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
import com.example.linkvest.ui.theme.screens.onboarding.OnboardingScreen // <- Import corretto!
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.linkvest.util.DataStoreManager
import android.view.View

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // FULLSCREEN COMPATIBILE
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
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
    val context = LocalContext.current
    var onboardingSeen by remember { mutableStateOf<Boolean?>(null) }
    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }
    val scope = rememberCoroutineScope()

    // Carica lo stato dall'onboarding e login dal DataStore
    LaunchedEffect(Unit) {
        onboardingSeen = DataStoreManager.isOnboardingCompleted(context)
        isLoggedIn = DataStoreManager.isLoggedIn(context)
    }

    // Mostra uno splash o uno schermo vuoto mentre carica
    if (onboardingSeen == null || isLoggedIn == null) {
        Box(Modifier.fillMaxSize().background(Color.White)) {}
        return
    }

    // Scegli la startDestination
    val startDestination = when {
        onboardingSeen == false -> "onboarding"
        isLoggedIn == true -> "home"
        else -> "login"
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable(
            "otp/{email}",
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpScreen(email = email, navController = navController)
        }
        composable("home") { HomeScreen(navController) }
        composable("drawer") { DrawerMenu(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("albums") { AlbumsScreen(navController) }
        composable("albumDetail") { AlbumDetailScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
    }
}