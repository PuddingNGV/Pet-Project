package com.example.feature_settings_screen.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.feature_settings_screen.domain.usecase.*

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase, private val safeSettingsUseCase: SafeSettingsUseCase) : ViewModel() {
    init {
        getSettingsUseCase.execute()
        safeSettingsUseCase.execute()
    }
}