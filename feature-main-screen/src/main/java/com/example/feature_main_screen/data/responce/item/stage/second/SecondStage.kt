package com.example.feature_main_screen.data.responce.item.stage.second


import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Any?,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("payloads")
    val payloads: Payloads,
    @SerializedName("reusable")
    val reusable: Boolean,
    @SerializedName("thrust")
    val thrust: Thrust
)