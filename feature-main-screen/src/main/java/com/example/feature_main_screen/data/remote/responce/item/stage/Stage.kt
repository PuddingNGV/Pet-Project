package com.example.feature_main_screen.data.remote.responce.item.stage

import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.StageLocal

data class Stage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("reusable")
    val reusable: Boolean
) {
    fun toLocalStage(idStage:Int): StageLocal = StageLocal(idStage, burnTimeSec, engines, fuelAmountTons, reusable)
}
