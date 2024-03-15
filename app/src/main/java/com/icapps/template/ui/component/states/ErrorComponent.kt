package com.icapps.template.ui.component.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icapps.template.R
import com.icapps.template.data.model.error.TemplateServiceError
import com.icapps.template.ui.component.common.TemplateButton
import com.icapps.template.ui.component.common.TemplateText
import com.icapps.template.ui.theme.TemplateTheme

@Composable
fun ErrorComponent(
    error: TemplateServiceError?,
    modifier: Modifier = Modifier,
    retryClickListener: (() -> Unit)? = null,
) {
    val formattedTitle = error?.message ?: stringResource(R.string.generic_error_title)
    val formattedMessage =
        error?.extraMessage ?: stringResource(R.string.generic_error_message)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp),
    ) {
        error?.errorCode?.let {
            TemplateText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = it,
                style = TemplateTheme.typography.label.copy(
                    textAlign = TextAlign.Center,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        TemplateText(
            modifier = Modifier
                .fillMaxWidth(),
            text = formattedTitle,
            style = TemplateTheme.typography.subtitle.copy(
                textAlign = TextAlign.Center,
            ),
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TemplateText(
            modifier = Modifier
                .fillMaxWidth(),
            text = formattedMessage,
            style = TemplateTheme.typography.body.copy(
                textAlign = TextAlign.Center,
            ),
        )
        if (retryClickListener != null) {
            Spacer(modifier = Modifier.height(12.dp))
            TemplateButton(
                text = stringResource(R.string.generic_button_retry),
                onClick = {
                    retryClickListener()
                },
            )
        }
    }
}

@Preview
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent(retryClickListener = {}, error = null)
}
