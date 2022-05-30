package com.example.feature_main_screen.data

import android.content.Context
import com.example.feature_main_screen.domain.models.*
import com.example.feature_main_screen.domain.repository.RocketRepo


private const val SHARED_PREFS_NAME = "shared_prefs"
private const val KEY_ROCKET = "key_rocket"

class RocketRepoImpl(private val context: Context) : RocketRepo {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    var saveInfo: String = String()

    override fun getRocket(): RocketInfo {

        val firstStage = StageInfo(engines = 1, fuelAmountTons = 1.0, burnTimeSec = 1)
        val secondStage = StageInfo(engines = 1, fuelAmountTons = 1.0, burnTimeSec = 1)
        return RocketInfo(rocketName = "", height = 1.0 , diameter = 1.0, weight = 1, payload = 1, firstFlight = 2, country = "", costPerLaunch = 1, firstStageInfo = firstStage, secondStageInfo = secondStage )
    }

    override fun saveRocket() {
        sharedPreferences.edit().putString(KEY_ROCKET, saveInfo).apply()
    }

}