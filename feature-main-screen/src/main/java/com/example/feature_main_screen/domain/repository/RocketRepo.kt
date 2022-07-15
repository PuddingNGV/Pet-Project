package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.data.local.entity.RocketDbEntity
import com.example.feature_main_screen.domain.models.RocketInfo
import kotlinx.coroutines.flow.Flow
import com.example.feature_main_screen.until.Resource

interface RocketRepo {

    suspend fun getRemoteRocket(): RocketInfo
    fun getLocalRocket(): Flow<Resource<List<RocketDbEntity>>>
}