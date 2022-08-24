package com.icapps.template.ui.screen.menu.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.icapps.template.ui.component.common.TemplateButton
import com.icapps.template.ui.component.common.TemplateText
import com.icapps.template.ui.screen.MainRoute
import com.icapps.template.ui.theme.TemplateTheme
import com.icapps.template.R

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TemplateText(
            text = stringResource(R.string.home_title),
            style = TemplateTheme.typography.title
        )
        Spacer(Modifier.height(8.dp))
        TemplateText(
            text = stringResource(R.string.home_description),
            style = TemplateTheme.typography.body
        )
        Spacer(Modifier.height(24.dp))
        TemplateButton(
            text = stringResource(R.string.home_button_settings),
        ) {
            navController.navigate(MainRoute.Settings.route)
        }
    }
}