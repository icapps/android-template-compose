package com.icapps.template.di

import android.content.Context
import com.icapps.template.data.storage.SettingStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideSettingStorage(
        @ApplicationContext context: Context
    ): SettingStorage {
        return SettingStorage(context)
    }
}