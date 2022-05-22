package com.example.feature_main_screen.domain.usecase

import android.icu.math.BigDecimal
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.models.StageInfo

class GetDataUseCase {

    fun execute(): RocketInfo {
        val firstStageInfo = StageInfo(27,1155.0,162)
        val secondStageInfo = StageInfo(1,1155.0,397)
        return RocketInfo("Falcon Heavy", 229.6, 39.9,3_125_735, 63_800, 1517961600000,"USA",
            BigDecimal(90000000), firstStageInfo, secondStageInfo)
    }
}