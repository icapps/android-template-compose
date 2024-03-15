package com.icapps.template.data.network

import com.icapps.template.data.model.responses.ExampleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    fun getExampleData(): Call<ExampleResponse>
}
