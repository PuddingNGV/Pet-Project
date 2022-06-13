package com.example.feature_main_screen.domain.usecase


import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.repository.RocketRepo
import kotlinx.coroutines.flow.Flow

class GetDataUseCase(private val rocketRepo: RocketRepo) {

    fun execute(): RocketInfo {
        return rocketRepo.getRocket()
    }
}