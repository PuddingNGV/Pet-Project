package com.example.feature_main_screen.data.local.entity

import com.example.feature_main_screen.domain.models.Mass

data class MassLocal(
    val kg: Int,
    val lb: Int
) {
    fun toModelsMass(): Mass = Mass(kg, lb)
}