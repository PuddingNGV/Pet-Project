package com.example.feature_settings_screen.domain.usecase

import com.example.feature_settings_screen.domain.repository.SettingsRepo
import com.example.feature_settings_screen.domain.models.SettingsParam
import kotlinx.coroutines.flow.Flow

class GetSettingsUseCase(private val settingsRepo: SettingsRepo) {
    fun execute(): Flow<SettingsParam> {
        return settingsRepo.getSettings()
    }
}