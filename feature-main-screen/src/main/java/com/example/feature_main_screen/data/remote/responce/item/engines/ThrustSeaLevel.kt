package com.example.feature_main_screen.data.remote.responce.item.engines


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.engines.ThrustSeaLevelLocal

data class ThrustSeaLevel(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
) {
    fun toLocalThrustSeaLevel(): ThrustSeaLevelLocal = ThrustSeaLevelLocal(kN, lbf)
}