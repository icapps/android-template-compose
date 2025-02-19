package com.icapps.template.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.icapps.template.ui.component.common.TemplateBottomSheet
import com.icapps.template.ui.navigation.main.MainDestination
import com.icapps.template.ui.navigation.main.mainNavGraph
import com.icapps.template.ui.theme.TemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController

    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class,
        ExperimentalMaterialNavigationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Disable fitSystemWindows (content under status bar for dialogs)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Set content
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            navController = rememberNavController(bottomSheetNavigator)

            // Apply theme
            TemplateTheme {
                TemplateBottomSheet(bottomSheetNavigator) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(TemplateTheme.colors.background)
                            .safeDrawingPadding(),
                        navController = navController,
                        startDestination = MainDestination.Menu,
                    ) {
                        mainNavGraph(
                            windowSizeClass = windowSizeClass,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun create(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
