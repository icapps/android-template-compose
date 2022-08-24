package com.icapps.template.ui.screen.menu

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.icapps.template.ui.navigation.NavRoute
import com.icapps.template.ui.screen.menu.example.ExampleScreen
import com.icapps.template.ui.screen.menu.home.HomeScreen

sealed class MenuRoute(val route: String) {
    object Home : NavRoute("home")
    object Example : NavRoute("example")
}

fun NavGraphBuilder.menuNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {

    composable(route = MenuRoute.Home.route) {
        HomeScreen(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
    composable(route = MenuRoute.Example.route) {
        ExampleScreen(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
}
