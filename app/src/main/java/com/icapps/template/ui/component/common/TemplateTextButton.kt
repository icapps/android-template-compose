package com.icapps.template.ui.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.icapps.template.ui.theme.TemplateTheme

@Composable
fun TemplateTextButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    colorOverride: Color? = null,
    onClick: (() -> Unit),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val alpha = when (enabled) {
        true -> 1F
        false -> 0.5F
    }
    Box(
        modifier = modifier
            .alpha(alpha)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ) { onClick() },
    ) {
        Text(
            text = text,
            style = TemplateTheme.typography.textButton,
            color = colorOverride ?: TemplateTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview
@Composable
private fun TemplateTextButtonPreview() {
    TemplateTextButton(
        text = "Demo Text Button",
        onClick = {},
    )
}
