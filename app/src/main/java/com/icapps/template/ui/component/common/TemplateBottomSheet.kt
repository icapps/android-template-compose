package com.icapps.template.ui.component.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.icapps.template.ui.theme.TemplateTheme

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun TemplateBottomSheet(
    navigator: BottomSheetNavigator,
    content: @Composable () -> Unit,
) {
    ModalBottomSheetLayout(
        bottomSheetNavigator = navigator,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetElevation = 0.dp,
        sheetBackgroundColor = TemplateTheme.colors.background,
        scrimColor = TemplateTheme.colors.sheetScrim,
        content = content,
    )
}
