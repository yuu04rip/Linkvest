package com.example.linkvest.ui.theme.screens.onboarding

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import com.example.linkvest.util.DataStoreManager

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}

@Composable
fun OnboardingScreen(navController: NavController) {
    // pageIndex 0 = splash, 1 = create, 2 = find, 3 = easy
    var pageIndex by remember { mutableStateOf(0) }
    val totalNavigablePages = 3 // Create, Find, Easy
    val lastPageIndex = totalNavigablePages
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    fun goToNextPage() {
        if (pageIndex < lastPageIndex) {
            pageIndex++
        } else {
            // Salva che l'onboarding è stato completato!
            coroutineScope.launch {
                DataStoreManager.setOnboardingCompleted(context, true)
                navController.navigate("login") {
                    popUpTo(0) // opzionale: pulisce la backstack
                }
            }
        }
    }

    fun skipOnboarding() {
        navController.navigate("login")
    }

    // Splash auto-avanza
    LaunchedEffect(pageIndex) {
        if (pageIndex == 0) {
            delay(2000)
            pageIndex = 1
        }
    }

    val currentPage = when (pageIndex) {
        0 -> OnboardingPage(
            bgColor = Color(0xFFF7E5D6),
            content = { SplashPage() }
        )
        1 -> OnboardingPage(
            bgColor = Color(0xFFE3F3FA),
            content = {
                CreateSearchDiscoverPage(
                    pageIndex = pageIndex,
                    pagesSize = totalNavigablePages,
                    lastPageIndex = lastPageIndex,
                    onNext = { goToNextPage() },
                    onSkip = { skipOnboarding() }
                )
            }
        )
        2 -> OnboardingPage(
            bgColor = Color(0xFFD9E9FA),
            content = { FindPartnerPage() }
        )
        3 -> OnboardingPage(
            bgColor = Color(0xFF4A90E2),
            content = { EasyFastOriginalPage() }
        )
        else -> null
    }

    currentPage?.let { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(page.bgColor)
        ) {
            page.content()
            // Mostra la navigation bar solo se NON siamo sulla splash (pagina 0)
            if (pageIndex in 1..3) {
                OnboardingNavigationBar(
                    pageIndex = pageIndex - 1, // 0 (Create), 1 (Find), 2 (Easy)
                    pagesSize = totalNavigablePages, // 3 pallini
                    onSkip = { skipOnboarding() },
                    onNext = { goToNextPage() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        }
    }
}