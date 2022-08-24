package com.icapps.template.data.state

import com.icapps.template.data.model.error.TemplateServiceError

sealed class NetworkDataState<out T> {
    object Loading : NetworkDataState<Nothing>()
    data class Error<T>(val data: TemplateServiceError? = null) : NetworkDataState<T>()
    data class Success<T>(val data: T) : NetworkDataState<T>()
}
