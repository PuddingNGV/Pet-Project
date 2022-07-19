package com.example.feature_main_screen.domain.usecase


import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import com.example.feature_main_screen.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetDataUseCase(private val rocketRepo: RocketRepo) {

    suspend fun executeRemote(): RocketInfo {
        return withContext(Dispatchers.IO){
            rocketRepo.getRemoteRocket()
        }
    }

    fun executeLocal(): Flow<Resource<List<RocketDbEntity>>> {
        return rocketRepo.getLocalRocket()

    }

}