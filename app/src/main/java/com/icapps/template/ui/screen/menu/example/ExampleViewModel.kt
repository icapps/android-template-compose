package com.icapps.template.ui.screen.menu.example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icapps.template.data.model.responses.ExampleResponse
import com.icapps.template.data.network.ApiService
import com.icapps.template.data.network.ExampleService
import com.icapps.template.data.network.ext.networkRequest
import com.icapps.template.data.state.NetworkDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val json: Json,
    private val apiService: ApiService,
    private val exampleService: ExampleService,
) : ViewModel() {

    var uiState by mutableStateOf<NetworkDataState<ExampleResponse>>(NetworkDataState.Loading)
        private set

    init {
        requestExampleData()
    }

    fun requestExampleData() {
        networkRequest(
            scope = viewModelScope,
            deserializer = json,
            request = { exampleService.getFact() },
            stateDispatcher = { uiState = it },
        )
    }
}
