package com.example.linkvest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = androidx.compose.material3.lightColorScheme(
            primary = Orange,
            secondary = DarkBlue,
            background = BackgroundBeige,
            surface = White,
            onPrimary = White,
            onSecondary = White,
            onBackground = DarkBlue,
            onSurface = DarkBlue
        ),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}