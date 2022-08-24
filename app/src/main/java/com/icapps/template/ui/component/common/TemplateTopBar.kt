package com.icapps.template.ui.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icapps.template.ui.component.common.interactionsource.NoRippleInteractionSource
import com.icapps.template.ui.theme.TemplateTheme

@Composable
fun TemplateTopBar(
    modifier: Modifier = Modifier,
    title: String,
    menuBuilder: @Composable ((RowScope) -> Unit)? = null,
    onBackButtonClicked: (() -> Unit)? = null,
) {
    TemplateTopBar(
        modifier = modifier.defaultMinSize(minHeight = 56.dp),
        titleBuilder = {
            Text(
                text = title,
                style = TemplateTheme.typography.appBarTitle,
                modifier = Modifier
                    .padding(16.dp)
            )
        },
        menuBuilder = menuBuilder,
        onBackButtonClicked = onBackButtonClicked,
    )
}

@Composable
fun TemplateTopBar(
    modifier: Modifier = Modifier,
    titleBuilder: @Composable (ColumnScope) -> Unit,
    menuBuilder: @Composable ((RowScope) -> Unit)? = null,
    onBackButtonClicked: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .background(TemplateTheme.colors.background)

    ) {
        // Back button
        onBackButtonClicked?.let {
            Icon(
                Icons.Outlined.ArrowBack,
                modifier = Modifier
                    .clickable(
                        interactionSource = NoRippleInteractionSource(),
                        indication = null
                    ) { it.invoke() }
                    .padding(16.dp)
                    .size(24.dp),
                tint = TemplateTheme.colors.onBackground,
                contentDescription = "Back",
            )
        }
        // Title container
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            titleBuilder(this)
        }

        // Menu container
        menuBuilder?.let { builder ->
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
            ) {
                builder(this)
            }
        }
    }
}

@Preview
@Composable
private fun TemplateTopBarPreview() {
    TemplateTopBar(title = "Title", onBackButtonClicked = {})
}