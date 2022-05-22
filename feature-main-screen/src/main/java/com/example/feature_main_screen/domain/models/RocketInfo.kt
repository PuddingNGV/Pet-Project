package com.example.feature_main_screen.domain.models

import android.icu.math.BigDecimal

class RocketInfo(
    val rocketName: String,
    val height: Double,
    val diameter: Double,
    val weight: Int,
    val payload: Int,

    val firstFlight: Long,
    val country: String,
    val costPerLaunch: BigDecimal,

    val firstStageInfo: StageInfo,
    val secondStageInfo: StageInfo
    ) {
}