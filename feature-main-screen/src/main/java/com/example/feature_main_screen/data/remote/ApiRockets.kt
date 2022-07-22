package com.example.feature_main_screen.data.remote

import com.example.feature_main_screen.data.remote.responce.RocketResponse
import com.example.feature_main_screen.data.remote.responce.item.RocketResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiRockets {

    @GET("./v4/rockets")
    suspend fun getRocketArrayList(): List<RocketResponseItem>

}