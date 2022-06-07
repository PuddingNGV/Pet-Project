package com.example.feature_main_screen.data.remote.responce.item.engines


import com.google.gson.annotations.SerializedName

data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)