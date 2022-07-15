package com.example.feature_main_screen.data

import android.util.Log
import androidx.room.withTransaction
import com.example.feature_main_screen.data.local.AppRocketDataBase
import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.data.remote.ApiRockets
import com.example.feature_main_screen.data.remote.responce.RocketResponse
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import com.example.feature_main_screen.until.Resource
import kotlinx.coroutines.delay
import javax.inject.Singleton
import com.example.feature_main_screen.until.networkBoundResource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.List.copyOf
import javax.inject.Inject

@Singleton
class RocketRepoImpl(private val apiRockets: ApiRockets, private val appRocketDataBase: AppRocketDataBase) : RocketRepo {

    private var response = emptyList<RocketResponseItem>()
    var entityList = emptyList<RocketDbEntity>()
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
                entityList = toLocalListRocketDbEntity(response)
            }
        }
        return response
    }


     private fun toLocalListRocketDbEntity(res: List<RocketResponseItem>): List<RocketDbEntity> {
         val b = mutableListOf<RocketDbEntity>()
        for (i in res.indices){
            b.add(res[i].toRocketDbEntity())
            Log.i("AAAA", "$i")
        }
         entityList = copyOf(b)
        return entityList
    }

//    override fun getLocalRocket() = networkBoundResource(
//        query = {
//            rocketDao.getAll()
//        },
//        fetch = {
//            delay(2000)
//            requestApi()
//        },
//        saveFetchResult = { rockets ->
//            appRocketDataBase.withTransaction {
//                rocketDao.deleteAll()
//                rocketDao.insertRocket(entityList)
//            }
//        }
//    )

    override fun getLocalRocket() = networkBoundResource(
            query = {
                rocketDao.getAll()
                    },
            fetch = {
                delay(2000)
                toLocalListRocketDbEntity(apiRockets.getRocketArrayList())
                    },
            saveFetchResult = { rockets ->
                appRocketDataBase.withTransaction {
                rocketDao.deleteAll()
                    Log.d("AAAA", "${rockets.size} Размер записей на добавления в бд")
                rocketDao.insertRocket(rockets)
                }
            }
        )




}