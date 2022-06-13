package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.domain.models.RocketInfo
import kotlinx.coroutines.flow.Flow

interface RocketRepo {

    fun getRocket(): RocketInfo
    fun saveRocket()
}