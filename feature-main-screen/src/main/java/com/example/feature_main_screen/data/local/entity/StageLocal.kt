package com.example.feature_main_screen.data.local.entity

import androidx.room.ColumnInfo

data class StageLocal(
    @ColumnInfo(name = "id_stage")val idStage: Int,
    @ColumnInfo(name = "burn_time_sec") val burnTimeSec: Int,
    val engines: Int,
    @ColumnInfo(name = "fuel_amount_tons") val fuelAmountTons: Double,
    val reusable: Boolean
)