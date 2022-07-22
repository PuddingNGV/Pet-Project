package com.example.feature_main_screen.data.local.entity.engines

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class EnginesLocal(
    @ColumnInfo(name = "engine_loss_max") val engineLossMax: Int,
    @Embedded val isp: IspLocal,
    val layout: String,
    val number: Int,
    val propellant1: String,
    val propellant2: String,
    @Embedded(prefix = "thrust_sea_level_") val thrustSeaLevel: ThrustSeaLevelLocal,
    @ColumnInfo(name = "thrust_to_weight") val thrustToWeight: Double,
    @Embedded(prefix = "thrust_vacuum_") val thrustVacuum: ThrustVacuumLocal,
    val type: String,
    val version: String
)