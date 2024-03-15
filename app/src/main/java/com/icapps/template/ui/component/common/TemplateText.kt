package com.icapps.template.ui.component.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.icapps.template.ui.theme.TemplateTheme

@Composable
fun TemplateText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TemplateTheme.typography.body,
    colorOverride: Color? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        style = style,
        color = colorOverride ?: style.color,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
    )
}

@Preview
@Composable
private fun TemplateTextPreview() {
    TemplateText(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat urna, blandit quis tortor id, egestas porta orci. Nulla tempus augue at quam ultricies, luctus convallis turpis hendrerit.",
    )
}
