package com.example.feature_settings_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.feature_settings_screen.domain.usecase.*
import com.example.feature_settings_screen.domain.models.SettingsParam

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase, private val safeSettingsUseCase: SafeSettingsUseCase) : ViewModel() {

    val settingsLiveData = load()

    fun safe(mapParamSettings:Map<Int, Boolean>) {
        val settingsParam = SettingsParam(
            mapParamSettings.getValue(0),
            mapParamSettings.getValue(1),
            mapParamSettings.getValue(2),
            mapParamSettings.getValue(3)
        )
        safeSettingsUseCase.execute(settingsParam)
    }

    private fun load(): LiveData<SettingsParam> {
        return getSettingsUseCase.execute().asLiveData()
    }
}