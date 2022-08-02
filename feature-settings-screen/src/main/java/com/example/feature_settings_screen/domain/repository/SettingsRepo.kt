package com.example.feature_settings_screen.domain.repository

interface SettingsRepo {
    fun safeSettings()
    fun getSettings()
}