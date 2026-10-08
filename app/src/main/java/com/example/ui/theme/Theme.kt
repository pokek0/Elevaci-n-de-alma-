package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.data.model.LiturgicalColor

enum class ThemeMode(val title: String, val subtitle: String) {
    SYSTEM("Según el sistema", "Sigue la configuración de tu teléfono"),
    LIGHT("Modo Claro", "Luz cálida y tonos serenos"),
    DARK("Modo Oscuro", "Fondo oscuro para lectura reposada")
}

fun getLiturgicalLightColorScheme(color: LiturgicalColor): ColorScheme {
    return lightColorScheme(
        primary = color.primaryLight,
        onPrimary = Color.White,
        primaryContainer = color.primaryContainerLight,
        onPrimaryContainer = color.onPrimaryContainerLight,
        secondary = color.accentLight,
        onSecondary = Color.White,
        secondaryContainer = color.primaryContainerLight,
        onSecondaryContainer = color.onPrimaryContainerLight,
        tertiary = color.accentLight,
        onTertiary = Color.White,
        background = LightSereneBackground,
        onBackground = LightTextPrimary,
        surface = LightSereneSurface,
        onSurface = LightTextPrimary,
        surfaceVariant = LightSereneSurfaceVariant,
        onSurfaceVariant = LightTextSecondary,
        outline = color.primaryLight.copy(alpha = 0.35f),
        outlineVariant = color.primaryLight.copy(alpha = 0.15f)
    )
}

fun getLiturgicalDarkColorScheme(color: LiturgicalColor): ColorScheme {
    return darkColorScheme(
        primary = color.primaryDark,
        onPrimary = Color(0xFF0F172A),
        primaryContainer = color.primaryContainerDark,
        onPrimaryContainer = color.primaryContainerLight,
        secondary = color.accentDark,
        onSecondary = Color(0xFF0F172A),
        secondaryContainer = color.primaryContainerDark,
        onSecondaryContainer = color.primaryContainerLight,
        tertiary = color.accentDark,
        onTertiary = Color(0xFF0F172A),
        background = DarkNightBackground,
        onBackground = DarkTextPrimary,
        surface = DarkNightSurface,
        onSurface = DarkTextPrimary,
        surfaceVariant = DarkNightSurfaceVariant,
        onSurfaceVariant = DarkTextSecondary,
        outline = color.primaryDark.copy(alpha = 0.35f),
        outlineVariant = color.primaryDark.copy(alpha = 0.15f)
    )
}

@Composable
fun MyApplicationTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    liturgicalColor: LiturgicalColor = LiturgicalColor.VERDE,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val isSystemDark = isSystemInDarkTheme()
    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDark -> getLiturgicalDarkColorScheme(liturgicalColor)
        else -> getLiturgicalLightColorScheme(liturgicalColor)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
