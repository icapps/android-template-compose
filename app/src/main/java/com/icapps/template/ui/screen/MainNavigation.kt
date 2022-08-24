package com.icapps.template.ui.screen

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.icapps.template.ui.navigation.NavRoute
import com.icapps.template.ui.screen.dialog.LanguageSwitcherSheet
import com.icapps.template.ui.screen.menu.MenuScreen
import com.icapps.template.ui.screen.settings.SettingsScreen

sealed class MainRoute(val route: String) {
    // Main routes
    object Menu : NavRoute("menu")
    object Settings : NavRoute("settings")

    // Dialogs
    object DialogLanguageSwitcher : NavRoute("dialog-language")

    /*
    Example route with parameters:
    object Example : NavRoute("example/{path}") {
        fun create(path: String) = "example/$path"
    }
    */
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.mainNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    composable(route = MainRoute.Menu.route) {
        MenuScreen(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
    composable(route = MainRoute.Settings.route) {
        SettingsScreen(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
    bottomSheet(route = MainRoute.DialogLanguageSwitcher.route) {
        LanguageSwitcherSheet(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
}