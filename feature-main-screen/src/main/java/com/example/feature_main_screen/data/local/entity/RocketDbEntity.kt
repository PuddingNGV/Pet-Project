package com.example.feature_main_screen.data.local.entity

import androidx.room.*
import com.example.feature_main_screen.data.local.entity.engines.Engines

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
    @Embedded(prefix = "engines_") val engines: Engines,
    @Embedded(prefix = "diameter_") val diameter: Diameter,
    @Embedded(prefix = "height_") val height: Height,
    @Embedded(prefix = "mass_") val mass: Mass,
    @Embedded(prefix = "landingLegs_") val landingLegs: LandingLegs,
    val description: String,
    val wikipedia: String,

    ) /*{
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
            true -> payloadWeights.kg
            false -> payloadWeights.lb
        },
        imageUlrList = List<String>,
        firstFlight = firstFlight,
        country = country,
        costPerLaunch = costPerLaunch,
        stageCount = stages,
        stageInfo = List<stage>,
    )
}
*/





@Entity (
    tableName = "stage",
    indices = [
        Index("id_stage", unique = true)
    ]
)
data class StageDbEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_stage")val idStage: Int,
    @ColumnInfo(name = "burn_time_sec") val burnTimeSec: Int,
    val engines: Int,
    @ColumnInfo(name = "fuel_amount_tons") val fuelAmountTons: Double,
    val reusable: Boolean
)

data class RocketWithStage(
    @Embedded val rocketDbEntity: RocketDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_stage"
    )
    val stageList:List<StageDbEntity>
)





@Entity (
    tableName = "image",
    indices = [
        Index("id_image", unique = true)
    ]
)
data class FlickrImageDbEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_image") val idImage:Int,
    val url:String
)

data class RocketWithFlickrImage(
    @Embedded val rocketDbEntity: RocketDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_image"
    )
    val stageList:List<FlickrImageDbEntity>
)








@Entity (
    tableName = "payload_weight",
    indices = [
        Index("payload_id", unique = true)
    ]
)
data class PayloadWeightDbEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "payload_id")val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)

data class RocketWithPayloadWeight(
    @Embedded val rocketDbEntity: RocketDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "payload_id"
    )
    val stageList:List<PayloadWeightDbEntity>
)
