package com.example.feature_main_screen.data

import com.example.feature_main_screen.data.remote.ApiRockets
import com.example.feature_main_screen.data.remote.responce.RocketResponse
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import com.example.feature_main_screen.data.remote.responce.item.stage.Stage
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.models.StageInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import javax.inject.Singleton

@Singleton
class RocketRepoImpl(private val apiRockets: ApiRockets) : RocketRepo {

    private var response = RocketResponse()

    override suspend fun getRocket(): RocketInfo {
        val requestScreenId = 2
        val requestParam =
            mapOf("height" to true, "diameter" to true, "mass" to true, "payload" to true)

        val answerApiRockets = requestApi().body()
        answerApiRockets.let {
            if (answerApiRockets != null) {
                response = answerApiRockets
            }
        }
        return DataProcessing().rocketProcessing(response[requestScreenId], requestParam)
    }

    private suspend fun requestApi() = apiRockets.getRocketArrayList()

}