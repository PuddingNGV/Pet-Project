package com.example.feature_main_screen.data.remote.responce.item.engines


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.engines.EnginesLocal

data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: Int,
    @SerializedName("isp")
    val isp: Isp,
    @SerializedName("layout")
    val layout: String?,
    @SerializedName("number")
    val number: Int,
    @SerializedName("propellant_1")
    val propellant1: String,
    @SerializedName("propellant_2")
    val propellant2: String,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum,
    @SerializedName("type")
    val type: String,
    @SerializedName("version")
    val version: String
)
{
    fun toEngineLocal(): EnginesLocal = EnginesLocal(
        engineLossMax,
        isp = isp.toLocalIsp(),
        let{layout.orEmpty()},
        number,
        propellant1,
        propellant2,
        thrustSeaLevel = thrustSeaLevel.toLocalThrustSeaLevel(),
        thrustToWeight,
        thrustVacuum = thrustVacuum.toLocalThrustVacuum(),
        type,
        version
    )
}