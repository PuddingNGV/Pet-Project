package com.example.feature_settings_screen.di

import android.content.Context
import com.example.feature_settings_screen.data.SettingsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.feature_settings_screen.domain.repository.SettingsRepo
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideSettingsRepo(@ApplicationContext context: Context): SettingsRepo {
        return SettingsRepoImpl(context)
    }
}