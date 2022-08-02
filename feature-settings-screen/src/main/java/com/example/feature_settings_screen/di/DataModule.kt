package com.example.feature_settings_screen.di

import com.example.feature_settings_screen.data.SettingsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.feature_settings_screen.domain.repository.SettingsRepo

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRocketRepo(): SettingsRepo {
        return SettingsRepoImpl()
    }
}