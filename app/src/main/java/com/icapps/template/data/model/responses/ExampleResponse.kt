package com.icapps.template.data.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class ExampleResponse(
    val fact : String?,
    val length : Int?
)