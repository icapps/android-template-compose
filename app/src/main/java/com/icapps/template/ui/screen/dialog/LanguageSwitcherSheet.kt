package com.icapps.template.ui.screen.dialog

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.icapps.template.R
import com.icapps.template.ui.component.common.TemplateButton
import com.icapps.template.ui.theme.TemplateTheme
import java.util.*

@Composable
fun LanguageSwitcherSheet(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
) {
    val appLanguages = listOf(
        Locale("nl"),
        Locale("en"),
    )

    Column(
        Modifier
            .navigationBarsPadding()
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.settings_language_dialog_title),
                style = TemplateTheme.typography.appBarTitle,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        appLanguages.forEach { locale ->
            TemplateButton(
                text = locale.displayName.replaceFirstChar { it.titlecase() },
                onClick = {
                    // Update locale
                    val localeList = LocaleListCompat.forLanguageTags(locale.toLanguageTag())
                    AppCompatDelegate.setApplicationLocales(localeList)
                },
            )
        }
    }
}
