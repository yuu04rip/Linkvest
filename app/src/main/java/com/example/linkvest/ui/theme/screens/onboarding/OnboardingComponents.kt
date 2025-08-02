package com.example.linkvest.ui.theme.screens.onboarding


import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Shadow

@Composable
fun OnboardingTitleShadowed(text: String) {
    Text(
        text = text,
        color = Color(0xFFFFE067),
        fontSize = 56.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .shadow(
                elevation = 7.dp,
                spotColor = Color(0xFFFFE067),
                ambientColor = Color(0xFFFFE067)
            )
    )
}

@Composable
fun OnboardingCirclesBackground() {
    val circles = listOf(
        Triple(160.dp, Color(0xFFD9B6A3), Offset(60f, 150f)),
        Triple(100.dp, Color(0xFFFFE067), Offset(220f, 340f)),
        Triple(80.dp, Color(0xFFD9B6A3), Offset(310f, 60f)),
        Triple(40.dp, Color(0xFFFFE067), Offset(180f, 500f)),
        Triple(90.dp, Color(0xFFD9B6A3), Offset(70f, 530f)),
        Triple(60.dp, Color(0xFFFFE067), Offset(320f, 520f)),
    )
    Box(modifier = Modifier.fillMaxSize()) {
        circles.forEach { (size, color, offset) ->
            Box(
                Modifier
                    .size(size)
                    .offset(x = offset.x.dp, y = offset.y.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

@Composable
fun OnboardingDots(current: Int, total: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        repeat(total) { idx ->
            Box(
                Modifier
                    .size(10.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        if (idx == current) Color(0xFFE47A44) else Color(0xFFD9B6A3),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun OnboardingButton(isLast: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 64.dp, height = 32.dp)
            .background(Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent, shape = RoundedCornerShape(32.dp))
                .border(2.dp, Color(0xFFE47A44), RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(Color(0xFFE47A44), shape = CircleShape)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFFE47A44),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Composable
fun OnboardingNavigationBar(
    pageIndex: Int,
    pagesSize: Int,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val orange = Color(0xFFE47A44)
    val dotSize = 24.dp
    val sliderWidth = 64.dp
    val sliderHeight = 32.dp

    // Stato locale per l'offset del pallino slider
    var sliderOffsetX by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // SKIP
        Text(
            text = "SKIP",
            color = orange,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier
                .padding(start = 32.dp, end = 12.dp)
                .clickable { onSkip() },
            style = LocalTextStyle.current.copy(
                shadow = Shadow(
                    color = orange,
                    offset = Offset(2f, 2f),
                    blurRadius = 0f
                )
            )
        )

        // 3 pallini fissi
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            repeat(pagesSize) { idx ->
                Box(
                    modifier = Modifier
                        .size(dotSize)
                        .clip(CircleShape)
                        .background(if (pageIndex == idx) orange else Color(0xFFD9B6A3))
                )
            }
        }

        Spacer(modifier = Modifier.width(24.dp))

        // SLIDER DI FIANCO AI DOTS
        Box(
            modifier = Modifier
                .size(width = sliderWidth, height = sliderHeight)
                .clip(RoundedCornerShape(50))
                .border(2.dp, orange, RoundedCornerShape(50))
                .background(Color.Transparent)
                .pointerInput(pageIndex) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            // Se il pallino è stato portato oltre metà slider, considera come "swipe"
                            val maxOffsetPx = with(density) { (sliderWidth - sliderHeight).toPx() }
                            if (sliderOffsetX > maxOffsetPx / 2) {
                                onNext()
                            }
                            // In tutti i casi, riporta il pallino a sinistra
                            sliderOffsetX = 0f
                        }
                    ) { change, dragAmount ->
                        val maxOffsetPx = with(density) { (sliderWidth - sliderHeight).toPx() }
                        sliderOffsetX = (sliderOffsetX + dragAmount).coerceIn(0f, maxOffsetPx)
                    }
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(sliderOffsetX.roundToInt(), 0) }
                    .size(sliderHeight)
                    .clip(CircleShape)
                    .background(orange),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(32.dp))
    }
}