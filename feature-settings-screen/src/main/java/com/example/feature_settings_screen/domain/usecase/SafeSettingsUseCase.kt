package com.example.feature_settings_screen.domain.usecase

import com.example.feature_settings_screen.domain.repository.SettingsRepo
import com.example.feature_settings_screen.domain.models.SettingsParam

class SafeSettingsUseCase(private val settingsRepo: SettingsRepo) {
    fun execute(settingsParam: SettingsParam) = settingsRepo.safeSettings(settingsParam)
}