package com.example.feature_main_screen.data.responce

data class FirstStage(
    val burn_time_sec: Any,
    val engines: Int,
    val fuel_amount_tons: Int,
    val reusable: Boolean,
    val thrust_sea_level: ThrustSeaLevelX,
    val thrust_vacuum: ThrustVacuumX
)