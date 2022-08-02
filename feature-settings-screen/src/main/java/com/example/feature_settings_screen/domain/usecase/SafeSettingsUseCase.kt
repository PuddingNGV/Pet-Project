package com.example.feature_settings_screen.domain.usecase

import com.example.feature_settings_screen.domain.repository.SettingsRepo

class SafeSettingsUseCase(private val settingsRepo: SettingsRepo) {
    fun execute() = settingsRepo.safeSettings()
}