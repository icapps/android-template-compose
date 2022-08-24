package com.icapps.template.ui.screen.menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.icapps.template.ui.component.common.TemplateBottomNavigation
import com.icapps.template.ui.component.common.TemplateBottomNavigationItem
import com.icapps.template.ui.component.common.TemplateScaffold
import com.icapps.template.ui.screen.menu.example.ExampleScreen
import com.icapps.template.ui.screen.menu.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
) {
    val menuNavController = rememberNavController()
    var currentNavRoute by rememberSaveable { mutableStateOf(MenuRoute.Home.route) }

    TemplateScaffold(
        bottomBar = {
            TemplateBottomNavigation(
                modifier = Modifier,
                currentRoute = currentNavRoute,
                items = listOf(
                    TemplateBottomNavigationItem(
                        Icons.Outlined.Home,
                        MenuRoute.Home.route,
                        false
                    ),
                    TemplateBottomNavigationItem(
                        Icons.Outlined.Info,
                        MenuRoute.Example.route,
                        false
                    ),
                ),
                onItemSelected = { route ->
                    currentNavRoute = route
                    menuNavController.navigate(route) {
                        popUpTo(menuNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = menuNavController,
            startDestination = currentNavRoute,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            menuNavGraph(
                navController = navController,
                windowSizeClass = windowSizeClass
            )
        }
    }
}