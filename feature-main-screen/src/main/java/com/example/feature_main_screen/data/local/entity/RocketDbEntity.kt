package com.example.feature_main_screen.data.local.entity

import androidx.room.*
import com.example.feature_main_screen.data.local.entity.engines.EnginesLocal
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.models.StageInfo
import java.util.List.copyOf

@Entity (
    tableName = "rockets",
    indices = [
        Index("id", unique = true)
    ]
        )
data class RocketDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val country: String,
    val company: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    @ColumnInfo(name = "success_rate_pct") val successRatePct: Int,
    val type: String,
    @ColumnInfo(name = "first_flight_") val firstFlight: String,
    @ColumnInfo(name = "cost_per_launch_") val costPerLaunch: Int,
    @Embedded(prefix = "engines_") val engines: EnginesLocal,
    @Embedded(prefix = "diameter_") val diameter: DiameterLocal,
    @Embedded(prefix = "height_") val height: HeightLocal,
    @Embedded(prefix = "mass_") val mass: MassLocal,
    @Embedded(prefix = "landingLegs_") val landingLegs: LandingLegsLocal,
    val description: String,
    val wikipedia: String,

    val stage: List<StageLocal>,
    val payloadWeights: List<PayloadWeightLocal>,
    val flickrImage: List<String>
    ) {

    fun toRocketInfo(heightType:Boolean, diameterType:Boolean, massType:Boolean, payloadType:Boolean):RocketInfo = RocketInfo(
        rocketName = name,
        height = when (heightType) {
            true -> height.meters
            false -> height.feet
        },
        diameter = when (diameterType) {
            true -> diameter.meters
            false -> diameter.feet
        },
        mass = when (massType) {
            true -> mass.kg
            false -> mass.lb
        },
        payload = when (payloadType) {
            true -> payloadWeights[0].kg
            false -> payloadWeights[0].lb
        },
        imageUlrList = flickrImage,
        firstFlight = firstFlight,
        country = country,
        costPerLaunch = costPerLaunch,
        stageCount = stages,
        stageInfo = toListStageInfo(stage),
    )
}


fun toStageInfo(stage: StageLocal):StageInfo = StageInfo(
    engines = stage.engines,
    fuelAmountTons = stage.fuelAmountTons,
    burnTimeSec = stage.burnTimeSec)

fun toListStageInfo(stage: List<StageLocal>):List<StageInfo> {
    val mListStageInfo = mutableListOf<StageInfo>()
    for (i in stage.indices){
        mListStageInfo.add(toStageInfo(stage[i]))
    }
    return copyOf(mListStageInfo)
}


