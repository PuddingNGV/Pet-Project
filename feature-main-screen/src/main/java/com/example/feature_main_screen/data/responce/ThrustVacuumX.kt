package com.example.feature_main_screen.data.responce


import com.google.gson.annotations.SerializedName

data class ThrustVacuumX(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)