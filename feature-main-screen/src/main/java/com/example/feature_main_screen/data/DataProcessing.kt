package com.example.feature_main_screen.data

import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import com.example.feature_main_screen.data.remote.responce.item.stage.Stage
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.models.StageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataProcessing {

    fun toLocalListRocketDbEntity(rocketResponseList: List<RocketResponseItem>): List<RocketDbEntity> {
        val mEntityList = mutableListOf<RocketDbEntity>()
        for (i in rocketResponseList.indices){
            mEntityList.add(rocketResponseList[i].toRocketDbEntity())
        }
        return java.util.List.copyOf(mEntityList)
    }

    fun toRocketInfo(listFlow: Flow<List<RocketDbEntity>>): Flow<List<RocketInfo>> {
        val rocketInfoList: Flow<List<RocketInfo>> = listFlow.map { list ->
            list.map { value ->
                value.toRocketInfo()
            }
        }
        return rocketInfoList
    }
    private fun toRocketInfoDeprecated(rocketDbEntity: List<RocketDbEntity>): List<RocketInfo> {
        val mEntityList = mutableListOf<RocketInfo>()
        rocketDbEntity.forEach{ value -> mEntityList.add(value.toRocketInfo())}
        return java.util.List.copyOf(mEntityList)
    }
    fun rocketProcessing(
        request: RocketResponseItem,
        param: Map<String, Boolean>
    ): RocketInfo {

        val heightParams = param["height"]
        val diameterParams = param["diameter"]
        val massParams = param["mass"]
        val payloadParams = param["payload"]

        val dataRocket = RocketInfo(
            request.name,
            request.height.toModelsHeight(),
            request.diameter.toModelsDiameter(),
            request.mass.toModelsMass(),
            request.payloadWeights[0].toModelsPayload(),
            request.flickrImages,
            request.firstFlight,
            request.country,
            request.costPerLaunch,
            request.stages,
            stageProcessing(request)
        )
        return dataRocket
    }

    private fun stageProcessing(request: RocketResponseItem): List<StageInfo> {
        val stageData: List<Stage> = listOfNotNull(
            request.firstStage,
            request.secondStage,
            request.thirdStage,
            request.fourthStage,
            request.fifthStage,
            request.sixthStage
        )
        val mutableListStage = mutableListOf<StageInfo>()
        for (i in stageData.indices) {
            val elementStageInfo = StageInfo(
                stageData[i].engines,
                stageData[i].fuelAmountTons,
                stageData[i].burnTimeSec
            )
            mutableListStage.add(elementStageInfo)
        }
        return mutableListStage
    }
}