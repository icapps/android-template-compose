package com.icapps.template.ui.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class TemplateDestination(
    @Transient open val route: String = "",
)
