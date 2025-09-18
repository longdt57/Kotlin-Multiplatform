package leegroup.module.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Typography(): Typography {
    val fontFamily = fontFamily()

    return Typography(
        displayLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 57.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 45.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 52.sp
        ),
        displaySmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 36.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 44.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 32.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 40.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 36.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 32.sp
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 11.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
    )
}
