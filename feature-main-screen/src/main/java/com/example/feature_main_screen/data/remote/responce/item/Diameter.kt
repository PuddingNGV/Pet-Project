package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.DiameterLocal
import com.example.feature_main_screen.domain.models.Diameter

data class Diameter(
    @SerializedName("feet")
    val feet: Double,
    @SerializedName("meters")
    val meters: Double
) {
    fun toLocalDiameter(): DiameterLocal = DiameterLocal(meters, feet)
    fun toModelsDiameter(): Diameter = Diameter(meters, feet)
}