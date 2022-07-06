package com.example.feature_main_screen.data.local.entity.engines

import androidx.room.ColumnInfo

data class Isp(
    @ColumnInfo(name = "sea_level") val seaLevel: Int,
    val vacuum: Int
)