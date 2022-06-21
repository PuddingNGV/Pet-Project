package com.example.feature_main_screen.data.remote.responce.item.stage

import com.google.gson.annotations.SerializedName

data class Stage(
    @SerializedName("burn_time_sec")
    var burnTimeSec: Int,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("reusable")
    val reusable: Boolean
)
