package com.example.feature_main_screen.data.responce

import com.example.feature_main_screen.data.Diameter
import com.example.feature_main_screen.data.Height
import com.example.feature_main_screen.data.Mass

data class RocketResponseItem(
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val diameter: Diameter,
    val engines: Engines,
    val first_flight: String,
    val first_stage: FirstStage,
    val flickr_images: List<String>,
    val height: Height,
    val id: String,
    val landing_legs: LandingLegs,
    val mass: Mass,
    val name: String,
    val payload_weights: List<PayloadWeight>,
    val second_stage: SecondStage,
    val stages: Int,
    val success_rate_pct: Int,
    val type: String,
    val wikipedia: String
)