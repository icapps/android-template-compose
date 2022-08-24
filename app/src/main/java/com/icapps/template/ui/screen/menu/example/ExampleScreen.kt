package com.icapps.template.ui.screen.menu.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.icapps.template.data.state.NetworkDataState
import com.icapps.template.ui.component.common.TemplateText
import com.icapps.template.ui.component.states.ErrorComponent
import com.icapps.template.ui.theme.TemplateTheme
import com.icapps.template.R
import com.icapps.template.ui.component.common.TemplateButton
import com.icapps.template.ui.component.common.TemplateTextButton

@Composable
fun ExampleScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    viewModel: ExampleViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TemplateText(
            text = stringResource(R.string.example_title),
            style = TemplateTheme.typography.title
        )
        Spacer(Modifier.height(16.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = TemplateTheme.colors.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            when (val state = viewModel.uiState) {
                is NetworkDataState.Success -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TemplateText(
                            text = stringResource(R.string.example_fact_title),
                            style = TemplateTheme.typography.subtitle
                        )
                        Spacer(Modifier.height(8.dp))
                        TemplateText(
                            text = state.data.fact ?: "",
                            style = TemplateTheme.typography.body
                        )
                        Spacer(Modifier.height(16.dp))
                        TemplateTextButton(text = stringResource(R.string.generic_button_reload)) {
                            viewModel.requestExampleData()
                        }
                    }
                }
                is NetworkDataState.Error -> {
                    ErrorComponent(state.data) {
                        viewModel.requestExampleData()
                    }
                }
                NetworkDataState.Loading -> {
                    CircularProgressIndicator(
                        color = TemplateTheme.colors.secondary,
                    )
                }
            }
        }
    }
}