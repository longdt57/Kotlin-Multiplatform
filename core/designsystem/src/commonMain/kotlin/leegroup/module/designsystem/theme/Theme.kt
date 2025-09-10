package leegroup.module.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = White100,
    onPrimary = Black100,
    secondary = GreyBold600,
    onSecondary = White100,

    tertiary = GreyBold800,
    onTertiary = White100,

    background = Black100,
    surface = Black100,
    onBackground = White100,
    onSurface = White100,
)

private val LightColorScheme = lightColorScheme(
    primary = Black100,
    onPrimary = White100,
    secondary = GreySoft950,
    onSecondary = Black100,

    tertiary = GreySoft800,
    onTertiary = Black100,

    background = White100,
    surface = White100,
    onBackground = Black100,
    onSurface = Black100,
)


@Composable
fun ComposeTheme(
    theme: ThemeType = ThemeType.SYSTEM,
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {

    val darkTheme: Boolean = when (theme) {
        ThemeType.LIGHT -> false
        ThemeType.DARK -> true
        else -> isSystemInDarkTheme()
    }

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes,
        content = content,
    )
}