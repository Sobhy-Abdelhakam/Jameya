package dev.sobhy.jameya.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TealGreenDark,
    onPrimary = Color.White,
    primaryContainer = TealGreen,
    onPrimaryContainer = Color.White,

    secondary = BabyBlue,
    onSecondary = Color.White,
    secondaryContainer = BabyBlue.copy(alpha = 0.7f),
    onSecondaryContainer = DarkGrey,

    tertiary = LightGreen,
    onTertiary = Color.White,
    tertiaryContainer = LightGreen.copy(alpha = 0.7f),
    onTertiaryContainer = DarkGrey,

    background = DarkGrey,
    onBackground = Color.White,
    surface = TealGreenDark,
    onSurface = Color.White,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = DarkGrey,

    error = ErrorRed,
    onError = Color.White,
    errorContainer = ErrorContainer,
    onErrorContainer = DarkGrey,

    outline = Outline,
    outlineVariant = Outline.copy(alpha = 0.6f),
    scrim = Color.Black,
    inverseSurface = LightGrey,
    inverseOnSurface = TealGreenDark,
    inversePrimary = TealGreen,
    surfaceTint = TealGreen
)

private val LightColorScheme = lightColorScheme(
    primary = TealGreen,
    onPrimary = Color.White,
    primaryContainer = LightGreen,
    onPrimaryContainer = TealGreenDark,

    secondary = BabyBlue,
    onSecondary = Color.White,
    secondaryContainer = BabyBlue.copy(alpha = 0.9f),
    onSecondaryContainer = DarkGrey,

    tertiary = LightGreen,
    onTertiary = Color.White,
    tertiaryContainer = LightGreen.copy(alpha = 0.9f),
    onTertiaryContainer = DarkGrey,

    background = LightGrey,
    onBackground = DarkGrey,
    surface = Color.White,
    onSurface = DarkGrey,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = DarkGrey,

    error = ErrorRed,
    onError = Color.White,
    errorContainer = ErrorContainer,
    onErrorContainer = DarkGrey,

    outline = Outline,
    outlineVariant = Outline.copy(alpha = 0.8f),
    scrim = Color.Black,
    inverseSurface = DarkGrey,
    inverseOnSurface = Color.White,
    inversePrimary = TealGreenDark,
    surfaceTint = TealGreen
)

@Composable
fun JameyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}