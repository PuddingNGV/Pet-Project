package com.example.feature_main_screen.domain.usecase


import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDataUseCase(private val rocketRepo: RocketRepo) {

    suspend fun execute(): RocketInfo {
        return withContext(Dispatchers.IO){
            rocketRepo.getRocket()
        }
    }
}