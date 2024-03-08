package com.icapps.template.data.network

import com.icapps.template.data.model.responses.ExampleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ExampleService {
    suspend fun getFact(): Result<ExampleResponse>
}

class ExampleServiceImpl(private val client: HttpClient) : ExampleService {
    override suspend fun getFact(): Result<ExampleResponse> = runCatching {
        client.get("/fact").body()
    }
}
