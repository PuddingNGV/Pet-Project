package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.domain.models.RocketInfo

interface RocketRepo {

    fun getRocket(): RocketInfo
    fun saveRocket()
}