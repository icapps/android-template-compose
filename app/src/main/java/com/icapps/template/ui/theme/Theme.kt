package com.icapps.template.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalThemeColors = staticCompositionLocalOf { TemplateColors }
private val LocalThemeTypography = staticCompositionLocalOf { TemplateTypography }

object TemplateTheme {

    val colors: TemplateColors
        @Composable
        get() = LocalThemeColors.current
    val typography: TemplateTypography
        @Composable
        get() = LocalThemeTypography.current
}

@Composable
fun TemplateAppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalThemeColors provides LocalThemeColors.current,
        LocalThemeTypography provides LocalThemeTypography.current,
        content = content
    )
}