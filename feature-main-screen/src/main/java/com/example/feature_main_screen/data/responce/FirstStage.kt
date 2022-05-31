package com.example.feature_main_screen.data.responce


import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Any?,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("reusable")
    val reusable: Boolean,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevelX,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuumX
)