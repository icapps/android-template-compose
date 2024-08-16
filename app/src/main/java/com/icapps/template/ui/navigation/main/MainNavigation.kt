package com.icapps.template.ui.navigation.main

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.icapps.template.ui.navigation.TemplateDestination
import com.icapps.template.ui.screen.dialog.LanguageSwitcherSheet
import com.icapps.template.ui.screen.menu.MenuScreen
import com.icapps.template.ui.screen.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
// TODO Get rid of 'route' once material-navigation has been updated to handle 'bottomSheet' type safe
sealed class MainDestination(override val route: String = "") : TemplateDestination() {
    // Main routes
    @Serializable
    data object Menu : MainDestination()

    @Serializable
    data object Settings : MainDestination()

    // Dialogs
    @Serializable
    data object DialogLanguageSwitcher : MainDestination(route = "dialog-language")

    /*
    Example route with parameters:
    @Serializable
    data class Example(
        val param1: Int,
        val param2: String,
    ) : MainDestination()
     */
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.mainNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
) {
    composable<MainDestination.Menu> {
        MenuScreen(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
    composable<MainDestination.Settings> {
        SettingsScreen(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
    bottomSheet(route = MainDestination.DialogLanguageSwitcher.route) {
        LanguageSwitcherSheet(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}
