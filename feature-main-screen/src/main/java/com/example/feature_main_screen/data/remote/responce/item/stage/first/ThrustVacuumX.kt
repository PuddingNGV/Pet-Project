package com.example.feature_main_screen.data.remote.responce.item.stage.first


import com.google.gson.annotations.SerializedName

data class ThrustVacuumX(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)