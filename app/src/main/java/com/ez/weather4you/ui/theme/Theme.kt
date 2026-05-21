package com.ez.weather4you.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ez.weather4you.ui.theme.colorscheme.DaySunnyColorScheme



@Composable
fun Weather4YouTheme(
    colorScheme: ColorScheme= DaySunnyColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = w4YTypography,
        shapes = shapes,
        content = content,
    )
}