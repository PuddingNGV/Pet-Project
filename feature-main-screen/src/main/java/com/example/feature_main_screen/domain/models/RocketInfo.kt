package com.example.feature_main_screen.domain.models


data class RocketInfo(
    val rocketName: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    val payload: Payload,
    val imageUlrList: List<String>,
    val firstFlight: String,
    val country: String,
    val costPerLaunch: Int,
    val stageCount: Int,
    val stageInfo: List<StageInfo>
)

