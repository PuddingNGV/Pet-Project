package com.example.feature_settings_screen.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.feature_settings_screen.domain.usecase.*
import com.example.feature_settings_screen.domain.models.SettingsParam

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase, private val safeSettingsUseCase: SafeSettingsUseCase) : ViewModel() {

    fun safe() {
        val settingsParam = SettingsParam(false,false,false,false)
        safeSettingsUseCase.execute(settingsParam)
    }

    fun load() {
        getSettingsUseCase.execute()
    }
}