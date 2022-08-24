package com.icapps.template.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

object TemplateTypography {

    val appBarTitle: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = TemplateColors.onBackground,
    )

    val title: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        color = TemplateColors.onBackground
    )
    val subtitle: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = TemplateColors.onBackground
    )
    val body: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        color = TemplateColors.onSurface
    )
    val label: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = TemplateColors.onSurface
    )

    val settingsRowLabel: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = TemplateColors.onBackground
    )

    val button: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = TemplateColors.onPrimary
    )
    val textButton: TextStyle = TextStyle(
        fontFamily = HKNova,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline,
        color = TemplateColors.primary
    )
}
