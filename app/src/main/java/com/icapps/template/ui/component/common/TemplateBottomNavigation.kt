package com.icapps.template.ui.component.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.icapps.template.ui.component.common.interactionsource.NoRippleInteractionSource
import com.icapps.template.ui.navigation.TemplateDestination
import com.icapps.template.ui.navigation.menu.MenuDestination
import com.icapps.template.ui.navigation.util.isSameDestination
import com.icapps.template.ui.theme.TemplateTheme

data class TemplateBottomNavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: TemplateDestination,
    val label: String? = null,
    val hasBadge: Boolean = false,
)

@Composable
fun TemplateBottomNavigationBar(
    menuItems: List<TemplateBottomNavigationItem>,
    navController: NavController,
    currentDestination: NavDestination?,
    isVisible: Boolean,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            TemplateBottomNavigation(
                modifier = Modifier.fillMaxWidth(),
                currentDestination = currentDestination,
                items = menuItems,
                onItemSelected = { route ->
                    if (currentDestination.isSameDestination(route)) return@TemplateBottomNavigation
                    navigateTo(navController, route)
                },
            )
        },
    )
}

@Composable
private fun TemplateBottomNavigation(
    modifier: Modifier = Modifier,
    items: List<TemplateBottomNavigationItem>,
    currentDestination: NavDestination?,
    itemWrapper: @Composable RowScope.(route: TemplateDestination, @Composable () -> Unit) -> Unit = { _, content ->
        Box(modifier = Modifier.weight(1F)) {
            content()
        }
    },
    onItemSelected: (route: TemplateDestination) -> Unit,
) {
    Surface(color = TemplateTheme.colors.background, shadowElevation = 8.dp) {
        Row(
            modifier = modifier
                .height(72.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .semantics {
                    collectionInfo = CollectionInfo(
                        rowCount = 1,
                        columnCount = items.size,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                itemWrapper(this, item.route) {
                    TemplateBottomNavigationItem(
                        item = item,
                        isSelected = currentDestination.isSameDestination(item.route),
                        onItemSelected = onItemSelected,
                    )
                }
            }
        }
    }
}

@Composable
fun TemplateBottomNavigationItem(
    item: TemplateBottomNavigationItem,
    isSelected: Boolean,
    onItemSelected: (route: TemplateDestination) -> Unit,
) {
    TemplateBottomNavigationItemView(
        modifier = Modifier
            .fillMaxWidth(),
        label = item.label,
        selectedIcon = item.selectedIcon,
        unselectedIcon = item.unselectedIcon,
        hasBadge = item.hasBadge,
        isSelected = isSelected,
        onClick = { onItemSelected(item.route) },
    )
}

@Composable
private fun TemplateBottomNavigationItemView(
    modifier: Modifier,
    label: String?,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    hasBadge: Boolean,
    isSelected: Boolean,
    unselectedColor: Color = TemplateTheme.colors.onBackground,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = NoRippleInteractionSource(),
                indication = null,
            )
            .padding(vertical = 16.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = when (isSelected) {
                    true -> selectedIcon
                    false -> unselectedIcon
                },
                contentDescription = null,
                colorFilter = when (isSelected) {
                    true -> ColorFilter.tint(
                        color = TemplateTheme.colors.primary,
                    )
                    false -> ColorFilter.tint(
                        color = unselectedColor,
                    )
                },
            )
            label?.let {
                Spacer(modifier = Modifier.height(4.dp))
                TemplateText(
                    modifier = Modifier.clearAndSetSemantics { /* Read out nothing for TalkBack */ },
                    text = label,
                    style = TemplateTheme.typography.label.copy(
                        color = when (isSelected) {
                            true -> TemplateTheme.colors.primary
                            false -> unselectedColor
                        },
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                    ),
                    maxLines = 1,
                )
            }
        }
        Box(
            modifier = Modifier.padding(start = 18.dp, bottom = 32.dp),
        ) {
            AnimatedVisibility(visible = hasBadge) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(TemplateTheme.colors.secondary),
                )
            }
        }
    }
}

@Preview
@Composable
private fun TemplateBottomNavigationPreview() {
    TemplateBottomNavigation(
        items = listOf(
            TemplateBottomNavigationItem(
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = MenuDestination.Home,
                label = "Home",
                hasBadge = false,
            ),
            TemplateBottomNavigationItem(
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.Favorite,
                route = MenuDestination.Home,
                label = "Favorites",
                hasBadge = false,
            ),
            TemplateBottomNavigationItem(
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
                route = MenuDestination.Home,
                label = "Search",
                hasBadge = false,
            ),
            TemplateBottomNavigationItem(
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart,
                route = MenuDestination.Home,
                label = "Shop",
                hasBadge = true,
            ),
            TemplateBottomNavigationItem(
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                route = MenuDestination.Home,
                label = "Settings",
                hasBadge = false,
            ),
        ),
        currentDestination = null,
        onItemSelected = { },
    )
}

private fun navigateTo(navController: NavController, route: TemplateDestination) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
