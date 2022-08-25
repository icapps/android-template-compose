package com.icapps.template.di

import android.content.Context
import com.chimerapps.niddler.core.AndroidNiddler
import com.chimerapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor
import com.icapps.template.R
import com.icapps.template.TemplateApp
import com.icapps.template.data.network.ApiService
import com.icapps.template.data.network.RequestConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.CookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRequestDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    @Named(HiltConstants.SERVICE_URL)
    fun providesServiceUrl(@ApplicationContext context: Context): String =
        context.getString(R.string.service_url)

    @Singleton
    @Provides
    fun providesNiddler(@ApplicationContext context: Context): AndroidNiddler =
        AndroidNiddler.Builder()
            .setPort(0)
            .setNiddlerInformation(AndroidNiddler.fromApplication(context as TemplateApp))
            .setMaxStackTraceSize(10)
            .build()

    @Singleton
    @Provides
    fun providesNiddlerInterceptor(niddler: AndroidNiddler): NiddlerOkHttpInterceptor =
        NiddlerOkHttpInterceptor(niddler, "Default")

    @Singleton
    @Provides
    fun providesJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        niddlerOkHttpInterceptor: NiddlerOkHttpInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cookieJar(CookieJar.NO_COOKIES)
            .callTimeout(RequestConstants.DEFAULT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .connectTimeout(RequestConstants.DEFAULT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(RequestConstants.DEFAULT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .writeTimeout(RequestConstants.DEFAULT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(niddlerOkHttpInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(
        @Named(HiltConstants.SERVICE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(RequestConstants.REQUEST_MEDIA_TYPE.toMediaType()))
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
