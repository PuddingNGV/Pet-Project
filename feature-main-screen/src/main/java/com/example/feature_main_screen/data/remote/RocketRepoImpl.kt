package com.example.feature_main_screen.data.remote

import android.content.Context
import com.example.feature_main_screen.data.remote.responce.RocketResponse
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import com.example.feature_main_screen.data.remote.responce.item.stage.Stage
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.models.StageInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


private const val SHARED_PREFS_NAME = "shared_prefs"
private const val KEY_ROCKET = "key_rocket"

class RocketRepoImpl(private val context: Context) : RocketRepo {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    private val initialAPI = ApiHelper(RetrofitBuilder.apiRockets)
    var saveInfo: String = String()
    var response = RocketResponse()


    override suspend fun getRocket(): RocketInfo {
        val requestScreenId = 2
        val requestParam =
            mapOf("height" to true, "diameter" to true, "mass" to true, "payload" to true)

        val answerApiRockets = initialAPI.getRockets().body()
        answerApiRockets.let {
            if (answerApiRockets != null) {
                response = answerApiRockets
            }
        }
        return dataProcessing(response[requestScreenId], requestParam)
    }

    private fun dataProcessing(
        request: RocketResponseItem,
        param: Map<String, Boolean>
    ): RocketInfo {

        val heightParams = param["height"]
        val diameterParams = param["diameter"]
        val massParams = param["mass"]
        val payloadParams = param["payload"]

        val dataRocket = RocketInfo(
            request.name,
            when (heightParams) {
                true -> request.height.meters
                else -> request.height.feet
            },
            when (diameterParams) {
                true -> request.diameter.meters
                else -> request.diameter.feet
            },
            when (massParams) {
                true -> request.mass.kg
                else -> request.mass.lb
            },
            when (payloadParams) {
                true -> request.payloadWeights[0].kg
                else -> request.payloadWeights[0].lb
            },
            request.flickrImages,
            request.firstFlight,
            request.country,
            request.costPerLaunch,
            request.stages,
            convertStage(request)
        )
        return dataRocket
    }


    /*
    private suspend fun requestApi(): RocketResponse {

        val job = GlobalScope.async(Dispatchers.IO) {
            val answer = initialAPI.getRockets().body()
            answer.let { return@async answer!! }
        }
        return job.await()
    }
    */
    private fun convertStage(request: RocketResponseItem): List<StageInfo> {
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

    override fun saveRocket() {
        sharedPreferences.edit().putString(KEY_ROCKET, saveInfo).apply()
    }


}