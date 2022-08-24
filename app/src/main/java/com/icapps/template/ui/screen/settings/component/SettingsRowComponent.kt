package com.icapps.template.ui.screen.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icapps.template.ui.component.common.TemplateText
import com.icapps.template.ui.theme.TemplateTheme

@Composable
fun SettingsRowComponent(
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TemplateText(
            text = label,
            style = TemplateTheme.typography.settingsRowLabel,
            modifier = Modifier.weight(1F)
        )
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = TemplateTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun SettingsRowComponentPreview() {
    SettingsRowComponent("Title") {
    }
}
