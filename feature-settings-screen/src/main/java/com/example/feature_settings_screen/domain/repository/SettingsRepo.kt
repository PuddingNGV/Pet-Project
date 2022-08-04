package com.example.feature_settings_screen.domain.repository

import com.example.feature_settings_screen.domain.models.SettingsParam

interface SettingsRepo {
    fun getSettings(): SettingsParam
    fun safeSettings(settingsParam: SettingsParam)
}