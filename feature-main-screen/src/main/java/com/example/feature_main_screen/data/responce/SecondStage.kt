package com.example.feature_main_screen.data.responce

data class SecondStage(
    val burn_time_sec: Any,
    val engines: Int,
    val fuel_amount_tons: Int,
    val payloads: Payloads,
    val reusable: Boolean,
    val thrust: Thrust
)