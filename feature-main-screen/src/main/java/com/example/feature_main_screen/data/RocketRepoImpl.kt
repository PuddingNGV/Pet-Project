package com.example.feature_main_screen.data

import androidx.room.withTransaction
import com.example.feature_main_screen.data.local.AppRocketDataBase
import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.data.remote.ApiRockets
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import kotlinx.coroutines.delay
import javax.inject.Singleton
import com.example.feature_main_screen.until.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import java.util.List.copyOf

@Singleton
class RocketRepoImpl(private val apiRockets: ApiRockets, private val appRocketDataBase: AppRocketDataBase) : RocketRepo {

    private var response = emptyList<RocketResponseItem>()
    private val rocketDao = appRocketDataBase.getRocketDao()


    override suspend fun getRemoteRocket(): RocketInfo {
        val requestScreenId = 2
        val requestParam =
            mapOf("height" to true, "diameter" to true, "mass" to true, "payload" to true)
        requestApi()
        return DataProcessing().rocketProcessing(response[requestScreenId], requestParam)
    }

    private suspend fun requestApi(): List<RocketResponseItem> {
        val answerApi = apiRockets.getRocketArrayList()
        answerApi.let {
            if (answerApi.isNotEmpty()) {
                response = answerApi
            }
        }
        return response
    }


    override fun getLocalRocket() = networkBoundResource(
            query = {
                DataProcessing().toRocketInfo(rocketDao.getAll())
                    },
            fetch = {
                delay(2000)
                DataProcessing().toLocalListRocketDbEntity(apiRockets.getRocketArrayList())
                    },
            saveFetchResult = { rockets ->
                appRocketDataBase.withTransaction {
                rocketDao.deleteAll()
                rocketDao.insertRocket(rockets)
                }
            }
        )
}