package com.icapps.template.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val DefaultColors = Colors()
private val LocalThemeColors = staticCompositionLocalOf { DefaultColors }
private val LocalThemeTypography = staticCompositionLocalOf { Typography(DefaultColors) }

object TemplateTheme {
    val colors: Colors
        @Composable
        get() = LocalThemeColors.current

    val typography: Typography
        @Composable
        get() = LocalThemeTypography.current
}

@Composable
fun TemplateTheme(
    colors: Colors = TemplateTheme.colors,
    typography: Typography = TemplateTheme.typography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalThemeColors provides colors,
        LocalThemeTypography provides typography,
        content = content,
    )
}
