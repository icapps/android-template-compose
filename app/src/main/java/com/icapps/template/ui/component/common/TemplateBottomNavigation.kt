package com.icapps.template.ui.component.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icapps.template.ui.component.common.interactionsource.NoRippleInteractionSource
import com.icapps.template.ui.theme.TemplateTheme

data class TemplateBottomNavigationItem(
    val icon: ImageVector,
    val route: String,
    val badge: Boolean,
)

@Composable
fun TemplateBottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: String,
    items: List<TemplateBottomNavigationItem>,
    onItemSelected: (String) -> Unit,
) {
    Column {
        Divider(color = TemplateTheme.colors.divider)
        Row(
            modifier = modifier
                .background(TemplateTheme.colors.background)
                .fillMaxWidth(),
        ) {
            items.forEach { item ->
                TemplateBottomNavigationItemView(
                    modifier = Modifier.weight(1F),
                    icon = item.icon,
                    badge = item.badge,
                    isSelected = item.route == currentRoute,
                    onClick = { onItemSelected(item.route) },
                )
            }
        }
    }
}

@Composable
private fun TemplateBottomNavigationItemView(
    modifier: Modifier,
    icon: ImageVector,
    badge: Boolean,
    isSelected: Boolean,
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
        val animatedColor = animateColorAsState(
            when (isSelected) {
                true -> TemplateTheme.colors.onBackground
                false -> TemplateTheme.colors.onSurface
            },
        )
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = animatedColor.value,
        )
        if (badge) {
            Box(
                modifier = Modifier.padding(start = 18.dp, bottom = 18.dp),
            ) {
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
                Icons.Outlined.Home,
                "1",
                false,
            ),
            TemplateBottomNavigationItem(
                Icons.Outlined.Favorite,
                "2",
                false,
            ),
            TemplateBottomNavigationItem(
                Icons.Outlined.Search,
                "3",
                false,
            ),
            TemplateBottomNavigationItem(
                Icons.Outlined.ShoppingCart,
                "4",
                true,
            ),
            TemplateBottomNavigationItem(
                Icons.Outlined.Settings,
                "5",
                false,
            ),
        ),
        currentRoute = "1",
        onItemSelected = { },
    )
}
