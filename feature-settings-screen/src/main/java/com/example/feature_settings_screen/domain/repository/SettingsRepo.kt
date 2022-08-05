package com.example.feature_settings_screen.domain.repository

import com.example.feature_settings_screen.domain.models.SettingsParam
import kotlinx.coroutines.flow.Flow

interface SettingsRepo {
    fun getSettings(): Flow<SettingsParam>
    fun safeSettings(settingsParam: SettingsParam)
}