package com.example.feature_settings_screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.example.feature_settings_screen.domain.usecase.*
import com.example.feature_settings_screen.domain.repository.SettingsRepo

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSettingsUseCase(settingsRepo: SettingsRepo): GetSettingsUseCase {
        return GetSettingsUseCase(settingsRepo)
    }

    @Provides
    fun provideSafeSettingsUseCase(settingsRepo: SettingsRepo): SafeSettingsUseCase {
        return SafeSettingsUseCase(settingsRepo)
    }
}