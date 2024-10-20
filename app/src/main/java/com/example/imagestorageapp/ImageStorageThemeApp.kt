package com.example.imagestorageapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.imagestorageapp.ui.theme.Pink40
import com.example.imagestorageapp.ui.theme.Pink80
import com.example.imagestorageapp.ui.theme.Purple40
import com.example.imagestorageapp.ui.theme.Purple80
import com.example.imagestorageapp.ui.theme.PurpleGrey40
import com.example.imagestorageapp.ui.theme.PurpleGrey80
import com.example.imagestorageapp.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun ImageStorageAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
