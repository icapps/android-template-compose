package com.icapps.template.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.icapps.template.BuildConfig
import com.icapps.template.R
import com.icapps.template.ui.component.common.TemplateScaffold
import com.icapps.template.ui.component.common.TemplateText
import com.icapps.template.ui.component.common.TemplateTopBar
import com.icapps.template.ui.screen.MainRoute
import com.icapps.template.ui.screen.settings.component.SettingsRowComponent
import com.icapps.template.ui.theme.TemplateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    TemplateScaffold(
        topBar = {
            TemplateTopBar(title = stringResource(R.string.settings_title)) {
                navController.popBackStack()
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(TemplateTheme.colors.surface)
                    .fillMaxWidth(),
            ) {
                SettingsRowComponent(label = stringResource(R.string.settings_language)) {
                    navController.navigate(MainRoute.DialogLanguageSwitcher.route)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TemplateText(
                text = "App version: ${BuildConfig.VERSION_NAME}",
                style = TemplateTheme.typography.label.copy(
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
