package com.icapps.template.data.network.ext

import android.util.Log
import com.icapps.template.data.model.error.TemplateServiceError
import com.icapps.template.data.state.NetworkDataState
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import kotlin.coroutines.CoroutineContext

fun <T> networkRequest(
    context: CoroutineContext = Dispatchers.IO,
    scope: CoroutineScope,
    deserializer: Json,
    request: Call<T>,
    stateDispatcher: (state: NetworkDataState<T>) -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        stateDispatcher(NetworkDataState.Error())
        Log.e("NetworkRequest", "NetworkRequest failed", throwable)
    }
    return scope.launch(exceptionHandler) {
        // Start with loading state
        stateDispatcher(NetworkDataState.Loading)
        // Execute request (on provided context)
        val response = withContext(context) { request.execute() }
        // Handle result
        when (response.isSuccessful) {
            true -> stateDispatcher(NetworkDataState.Success(response.body()!!))
            false -> try {
                // Attempt deserializing error response
                stateDispatcher(
                    NetworkDataState.Error(
                        deserializer.decodeFromString<TemplateServiceError>(
                            response.errorBody().toString()
                        )
                    )
                )
            } catch (e: Exception) {
                // If deserialization fails print stacktrace & return default error
                e.printStackTrace()
                stateDispatcher(NetworkDataState.Error())
            }
        }
    }
}