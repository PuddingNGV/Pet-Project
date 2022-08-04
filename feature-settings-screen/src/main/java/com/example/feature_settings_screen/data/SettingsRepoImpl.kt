package com.example.feature_settings_screen.data

import android.content.Context
import javax.inject.Singleton
import com.example.feature_settings_screen.domain.repository.SettingsRepo
import com.example.feature_settings_screen.domain.models.SettingsParam
import dagger.hilt.android.qualifiers.ApplicationContext

private const val SHARED_PREFS_SETTINGS = "shared_prefs_settings"
private const val KEY_HEIGHT_PARAM = "height_param_settings"
private const val KEY_DIAMETER_PARAM = "diameter_param_settings"
private const val KEY_MASS_PARAM = "mass_param_settings"
private const val KEY_PAYLOAD_PARAM = "payload_param_settings"

@Singleton
class SettingsRepoImpl(@ApplicationContext private val context: Context) : SettingsRepo {

   private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE)

    override fun safeSettings(settingsParam: SettingsParam) {
        sharedPreferences.edit().putBoolean(KEY_HEIGHT_PARAM, settingsParam.heightParam).apply()
        sharedPreferences.edit().putBoolean(KEY_DIAMETER_PARAM, settingsParam.diameterParam).apply()
        sharedPreferences.edit().putBoolean(KEY_MASS_PARAM, settingsParam.massParam).apply()
        sharedPreferences.edit().putBoolean(KEY_PAYLOAD_PARAM, settingsParam.payloadParam).apply()
    }

    override fun getSettings() = SettingsParam(
            heightParam = sharedPreferences.getBoolean(KEY_HEIGHT_PARAM, false),
            diameterParam = sharedPreferences.getBoolean(KEY_DIAMETER_PARAM, false),
            massParam = sharedPreferences.getBoolean(KEY_MASS_PARAM, false),
            payloadParam = sharedPreferences.getBoolean(KEY_PAYLOAD_PARAM, false)
        )
}