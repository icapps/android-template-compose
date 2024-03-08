package com.icapps.template.data.network.ext

import com.icapps.template.data.model.error.TemplateServiceError
import com.icapps.template.data.state.NetworkDataState
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import timber.log.Timber

inline fun <T> networkRequest(
    scope: CoroutineScope,
    deserializer: Json,
    crossinline request: suspend () -> Result<T>,
    crossinline stateDispatcher: suspend (state: NetworkDataState<T>) -> Unit,
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "NetworkRequest failed")
        scope.launch {
            stateDispatcher.invoke(NetworkDataState.Error())
        }
    }

    return scope.launch(exceptionHandler) {
        // Start with loading state
        stateDispatcher.invoke(NetworkDataState.Loading)

        // Execute request (on provided context) and handle result
        try {
            val response = request.invoke()
            stateDispatcher.invoke(NetworkDataState.Success(response.getOrThrow()))
        } catch (e: ClientRequestException) {
            stateDispatcher.invoke(
                NetworkDataState.Error(
                    deserializer.decodeFromString<TemplateServiceError>(
                        e.response.status.toString(),
                    ),
                ),
            )
        } catch (e: Exception) {
            e.printStackTrace()
            stateDispatcher.invoke(NetworkDataState.Error())
        }
    }
}
