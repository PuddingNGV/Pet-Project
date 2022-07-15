package com.example.feature_main_screen.data.remote.responce.item.engines


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.engines.ThrustVacuumLocal

data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
) {
    fun toLocalThrustVacuum(): ThrustVacuumLocal = ThrustVacuumLocal(kN, lbf)
}