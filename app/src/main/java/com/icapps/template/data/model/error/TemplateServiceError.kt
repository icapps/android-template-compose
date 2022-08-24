package com.icapps.template.data.model.error

import kotlinx.serialization.Serializable

@Serializable
data class TemplateServiceError(
    val status: Int? = null,
    val errorCode: String,
    val message: String,
    val extraMessage: String? = null,
)