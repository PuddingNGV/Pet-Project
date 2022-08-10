package com.example.feature_main_screen.data.local.entity

import com.example.feature_main_screen.domain.models.Height

data class HeightLocal(
    val meters: Double,
    val feet: Double
) {
    fun toModelsHeight(): Height = Height(meters, feet)
}
