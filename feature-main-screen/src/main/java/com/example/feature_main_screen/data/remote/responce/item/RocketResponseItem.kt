package com.example.feature_main_screen.data.remote.responce.item


import com.example.feature_main_screen.data.local.entity.PayloadWeightLocal
import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.data.remote.responce.item.engines.Engines
import com.example.feature_main_screen.data.remote.responce.item.stage.Stage
import com.google.gson.annotations.SerializedName

data class RocketResponseItem(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("boosters")
    val boosters: Int,
    @SerializedName("company")
    val company: String,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("diameter")
    val diameter: Diameter,
    @SerializedName("engines")
    val engines: Engines,
    @SerializedName("first_flight")
    val firstFlight: String,
    @SerializedName("first_stage")
    val firstStage: Stage?,
    @SerializedName("second_stage")
    val secondStage: Stage?,
    @SerializedName("third_stage")
    val thirdStage: Stage?,
    @SerializedName("fourth_stage")
    val fourthStage: Stage?,
    @SerializedName("fifth_stage")
    val fifthStage: Stage?,
    @SerializedName("sixth_stage")
    val sixthStage: Stage?,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    @SerializedName("height")
    val height: Height,
    @SerializedName("id")
    val id: String,
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs,
    @SerializedName("mass")
    val mass: Mass,
    @SerializedName("name")
    val name: String,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight>,
    @SerializedName("stages")
    val stages: Int,
    @SerializedName("success_rate_pct")
    val successRatePct: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("wikipedia")
    val wikipedia: String
) {
    fun toRocketDbEntity(): RocketDbEntity = RocketDbEntity(
        id = id,
        name = name,
        country = name,
        company = company,
        active = active,
        stages = stages,
        boosters = boosters,
        successRatePct = successRatePct,
        type = type,
        firstFlight = firstFlight,
        costPerLaunch = costPerLaunch,
        engines = engines.toEngineLocal(),
        diameter = diameter.toLocalDiameter(),
        height = height.toLocalHeight(),
        mass = mass.toLocalMass(),
        landingLegs = landingLegs.toLocalLandingLegs(),
        description = description,
        wikipedia = wikipedia,
        stage = listOfNotNull(
            firstStage?.toLocalStage(1),
            secondStage?.toLocalStage(2),
            thirdStage?.toLocalStage(3),
            fourthStage?.toLocalStage(4),
            fifthStage?.toLocalStage(5),
            sixthStage?.toLocalStage(6)
        ),
        payloadWeights = toLocalListPayloadWeight(payloadWeights),
        flickrImage = flickrImages
    )
    private fun toLocalListPayloadWeight(payloadWeights:List<PayloadWeight>): List<PayloadWeightLocal> {
        val mListPayloadWeight = mutableListOf<PayloadWeightLocal>()
        for (i in payloadWeights.indices){
            mListPayloadWeight.add(payloadWeights[i].toLocalPayloadWeight())
        }
        return java.util.List.copyOf(mListPayloadWeight)
    }
}