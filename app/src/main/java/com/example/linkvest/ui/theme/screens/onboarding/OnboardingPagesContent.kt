package com.example.linkvest.ui.theme.screens.onboarding

import android.R.attr.textSize
import android.graphics.Color.alpha
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Shadow
@Preview(showBackground = true)
@Composable
fun SplashPagePreview() {
    SplashPage()
}

@Composable
fun SplashPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF7E5D6),
                        Color(0xFFF3ECD6)
                    )
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 230.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 140.dp, height = 120.dp),
                contentAlignment = Alignment.Center
            ) {
                // Ombra ovale
                Canvas(
                    modifier = Modifier
                        .size(110.dp, 90.dp)
                        .graphicsLayer {
                            translationX = 6f
                            translationY = 12f
                        }
                ) {
                    drawOval(
                        color = Color(0x45000000), // nero trasparente
                        topLeft = Offset(0f, 0f),
                        size = size
                    )
                }
                // Ovale blu
                Canvas(
                    modifier = Modifier.size(110.dp, 90.dp)
                ) {
                    drawOval(
                        color = Color(0xFF2E37E2),
                        topLeft = Offset(0f, 0f),
                        size = size
                    )
                }
                // Ombra arco alto-sinistra
                Canvas(
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            translationX = -36f + 4f
                            translationY = -34f + 6f
                        }
                ) {
                    drawArc(
                        color = Color(0x45000000),
                        startAngle = 205f,
                        sweepAngle = 62f,
                        useCenter = false,
                        style = Stroke(width = 21f, cap = StrokeCap.Round)
                    )
                }
                // Arco arancione alto-sinistra
                Canvas(
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            translationX = -36f
                            translationY = -34f
                        }
                ) {
                    drawArc(
                        color = Color(0xFFE47A44),
                        startAngle = 205f,
                        sweepAngle = 62f,
                        useCenter = false,
                        style = Stroke(width = 21f, cap = StrokeCap.Round)
                    )
                }
                // Ombra arco basso-destra
                Canvas(
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            translationX = 36f + 4f
                            translationY = 34f + 6f
                        }
                ) {
                    drawArc(
                        color = Color(0x45000000),
                        startAngle = 25f,
                        sweepAngle = 62f,
                        useCenter = false,
                        style = Stroke(width = 21f, cap = StrokeCap.Round)
                    )
                }
                // Arco arancione basso-destra
                Canvas(
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            translationX = 36f
                            translationY = 34f
                        }
                ) {
                    drawArc(
                        color = Color(0xFFE47A44),
                        startAngle = 25f,
                        sweepAngle = 62f,
                        useCenter = false,
                        style = Stroke(width = 21f, cap = StrokeCap.Round)
                    )
                }
            }
            Spacer(Modifier.height(54.dp))
            // Testo "name" con solo ombra sotto le lettere
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                Text(
                    text = "name",
                    color = Color(0xFFE47A44),
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    // Ombra solo sotto il testo, nessun quadrato
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color(0x33000000),
                            offset = Offset(4f, 8f),
                            blurRadius = 16f
                        )
                    )
                )
            }
        }
    }
}
@Composable
fun CreateSearchDiscoverPage(
    pageIndex: Int,
    pagesSize: Int,
    lastPageIndex: Int,
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F3FA),
                        Color(0xFFF7FCFF)
                    )
                )
            )
    ) {
        // Titoli centrati
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 70.dp, bottom = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingTitleWithUnderlineShadow("Create")
            Spacer(Modifier.height(35.dp))
            OnboardingTitleWithUnderlineShadow("Search")
            Spacer(Modifier.height(35.dp))
            OnboardingTitleWithUnderlineShadow("Discover")
        }

    }
}

@Composable
fun OnboardingTitleWithUnderlineShadow(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Testo con ombra disegnata manualmente
            Box(
                modifier = Modifier
                    .drawWithContent {
                        drawIntoCanvas { canvas ->
                            val paint = android.graphics.Paint().apply {
                                color = android.graphics.Color.parseColor("#F6B48B")
                                alpha = 120
                                textSize = 74.sp.toPx()
                                isFakeBoldText = true
                            }

                            // Calcola posizione centrale orizzontale del testo
                            val textWidth = paint.measureText(text)
                            val x = (size.width - textWidth) / 2
                            val y = 74.sp.toPx() + 9f // y = baseline + offset

                            canvas.nativeCanvas.drawText(text, x, y, paint)
                        }

                        // Disegna il contenuto normale (cioè il testo sopra)
                        drawContent()
                    }
            ) {
                Text(
                    text = text,
                    color = Color(0xFFE47A44),
                    fontSize = 74.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(2.dp)
                    .background(
                        color = Color(0xFFD8D8D8),
                        shape = RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}


@Composable
fun FindPartnerPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFD9E9FA),
                        Color(0xFFCCE0F5)
                    )
                )
            )
    ) {
        // Sfondo con 12 cerchi geometrici, distribuiti su tutta la pagina
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val circles = listOf(
                Triple(Offset(0.11f * width, 0.13f * height), 0.10f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.45f * width, 0.07f * height), 0.055f * width, Color(0xFFE7D77A)),
                Triple(Offset(0.87f * width, 0.05f * height), 0.04f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.2f * width, 0.23f * height), 0.04f * width, Color(0xFFE7D77A)),
                Triple(Offset(0.55f * width, 0.17f * height), 0.08f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.10f * width, 0.34f * height), 0.03f * width, Color(0xFFE7D77A)),
                Triple(Offset(0.42f * width, 0.42f * height), 0.09f * width, Color(0xFFE7D77A)),
                Triple(Offset(0.85f * width, 0.28f * height), 0.04f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.75f * width, 0.60f * height), 0.07f * width, Color(0xFFE7D77A)),
                Triple(Offset(0.18f * width, 0.82f * height), 0.06f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.80f * width, 0.86f * height), 0.11f * width, Color(0xFFE7B47A)),
                Triple(Offset(0.5f * width, 0.95f * height), 0.05f * width, Color(0xFFE7D77A)),
            )
            circles.forEach { (offset, radius, color) ->
                drawCircle(
                    color = Color.White.copy(alpha = 0.6f),
                    radius = radius + 8f,
                    center = offset,
                    style = Stroke(width = 8f)
                )
                drawCircle(
                    color = color.copy(alpha = 0.85f),
                    radius = radius,
                    center = offset
                )
            }
        }

        // Testo centrale con SOLO ombra sotto le lettere
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "find your",
                color = Color(0xFF29557A),
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color(0xF21E3F58),
                        offset = androidx.compose.ui.geometry.Offset(6f, 12f),
                        blurRadius = 0f
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "future",
                color = Color(0xFF29557A),
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color(0xF21E3F58),
                        offset = androidx.compose.ui.geometry.Offset(6f, 12f),
                        blurRadius = 0f
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "partner",
                color = Color(0xFF29557A),
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color(0xF21E3F58),
                        offset = androidx.compose.ui.geometry.Offset(6f, 12f),
                        blurRadius = 0f
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
fun EasyFastOriginalPage() {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4A90E2),
                        Color(0xFF5EB3F7)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Card sfumata con testo dentro (simile a un grande box con angoli arrotondati)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(40.dp))
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFEAF5FF),
                            Color(0xFFEAF5FF).copy(alpha = 0.89f),
                            Color(0xFFC7E3FD).copy(alpha = 0.85f)
                        ),
                        center = Offset(600f, 350f),
                        radius = 780f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 38.dp, vertical = 14.dp),
                horizontalAlignment = Alignment.Start
            ) {
                EasyShadowText("easy")
                Spacer(Modifier.height(36.dp))
                EasyShadowText("fast")
                Spacer(Modifier.height(36.dp))
                EasyShadowText("original")
            }
        }
    }
}

@Composable
private fun EasyShadowText(text: String) {
    // Prima l'ombra sotto (giallo spento), poi il testo sopra
    Box {
        Text(
            text = text,
            fontSize = 65.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFE9E6B0),
            style = TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color(0xFFB0A856),
                    offset = Offset(6f, 12f),
                    blurRadius = 0f
                )
            ),
            lineHeight = 70.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = text,
            fontSize = 65.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFF9C4),
            lineHeight = 70.sp,
            textAlign = TextAlign.Start,
        )
    }
}