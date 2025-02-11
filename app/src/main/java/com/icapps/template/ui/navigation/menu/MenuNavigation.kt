package com.icapps.template.ui.navigation.menu

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.icapps.template.ui.navigation.TemplateDestination
import com.icapps.template.ui.screen.menu.example.ExampleScreen
import com.icapps.template.ui.screen.menu.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class MenuDestination : TemplateDestination() {
    @Serializable
    data object Home : MenuDestination()

    @Serializable
    data object Example : MenuDestination()
}

fun NavGraphBuilder.menuNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
) {
    composable<MenuDestination.Home> {
        HomeScreen(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
    composable<MenuDestination.Example> {
        ExampleScreen(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}
