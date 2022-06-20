package com.example.feature_main_screen.data.remote

class ApiHelper(private val apiRockets: ApiRockets) {

    suspend fun getRockets() = apiRockets.getRocketArrayList()

}