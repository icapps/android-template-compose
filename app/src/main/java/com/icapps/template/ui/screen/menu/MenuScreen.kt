package com.icapps.template.ui.screen.menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.icapps.template.ui.component.common.TemplateBottomNavigationBar
import com.icapps.template.ui.component.common.TemplateBottomNavigationItem
import com.icapps.template.ui.component.common.TemplateScaffold
import com.icapps.template.ui.navigation.menu.MenuDestination
import com.icapps.template.ui.navigation.menu.menuNavGraph
import com.icapps.template.ui.navigation.util.isBottomBarItem

@Composable
fun MenuScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
) {
    val menuNavController = rememberNavController()

    TemplateScaffold(
        bottomBar = {
            val navBackStackEntry by menuNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            // Hold onto internal destination for bottom bar to avoid issue where if you navigate to a
            // route where the bottom bar is still visible that it will unselect the item where you are at
            // like for example navigating to a bottom sheet
            var currentInternalDestination by remember {
                mutableStateOf<NavDestination?>(
                    null,
                )
            }

            if (currentDestination.isBottomBarItem()) {
                currentInternalDestination = currentDestination
            }

            TemplateBottomNavigationBar(
                menuItems = listOf(
                    TemplateBottomNavigationItem(
                        unselectedIcon = Icons.Outlined.Home,
                        selectedIcon = Icons.Filled.Home,
                        route = MenuDestination.Home,
                    ),
                    TemplateBottomNavigationItem(
                        unselectedIcon = Icons.Outlined.Info,
                        selectedIcon = Icons.Filled.Info,
                        route = MenuDestination.Example,
                    ),
                ),
                navController = menuNavController,
                currentDestination = currentInternalDestination,
                isVisible = true,
            )
        },
    ) {
        NavHost(
            navController = menuNavController,
            startDestination = MenuDestination.Home,
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            menuNavGraph(
                navController = navController,
                windowSizeClass = windowSizeClass,
            )
        }
    }
}
