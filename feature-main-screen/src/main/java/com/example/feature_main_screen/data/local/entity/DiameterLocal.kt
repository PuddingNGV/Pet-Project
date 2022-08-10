package com.example.feature_main_screen.data.local.entity

import com.example.feature_main_screen.domain.models.Diameter

data class DiameterLocal(
    val meters: Double,
    val feet: Double
) {
    fun toModelsDiameter(): Diameter = Diameter(meters, feet)
}