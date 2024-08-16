package com.icapps.template.ui.navigation.util

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.icapps.template.ui.navigation.TemplateDestination
import com.icapps.template.ui.navigation.menu.MenuDestination

fun NavDestination?.isBottomBarItem() =
    listOf(
        MenuDestination.Home,
        MenuDestination.Example,
    ).any { this?.hasRoute(it::class) == true }

fun NavDestination?.isSameDestination(destination: TemplateDestination) =
    this?.hasRoute(destination::class) == true
