package com.example.feature_main_screen.domain.models

class RocketInfo(
    val rocketName: String,
    val height: Double,
    val diameter: Double,
    val weight: Int,
    val payload: Int,
    val imageUlrList: List<String>,
    val firstFlight: String,
    val country: String,
    val costPerLaunch: Int,
    val stageCount: Int,
    val stageInfo: List<StageInfo>
)