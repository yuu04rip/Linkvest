package com.example.linkvest.ui.theme.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun getOnboardingPages(
    pageIndex: Int,
    pagesSize: Int,
    lastPageIndex: Int,
    onNext: () -> Unit,
    onSkip: () -> Unit
): List<OnboardingPage> = listOf(
    OnboardingPage(
        bgColor = Color(0xFFF7E5D6),
        content = { SplashPage() }
    ),
    OnboardingPage(
        bgColor = Color(0xFFE3F2FD),
        content = {
            CreateSearchDiscoverPage(
                pageIndex = pageIndex,
                pagesSize = pagesSize,
                lastPageIndex = lastPageIndex,
                onNext = onNext,
                onSkip = onSkip
            )
        }
    ),
    OnboardingPage(
        bgColor = Color(0xFFD9E9FA),
        content = { FindPartnerPage() }
    ),
    OnboardingPage(
        bgColor = Color(0xFF4A90E2),
        content = { EasyFastOriginalPage() }
    )
)

data class OnboardingPage(
    val bgColor: Color,
    val content: @Composable () -> Unit
)