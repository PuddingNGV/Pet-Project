package com.example.feature_main_screen.data.remote

import retrofit2.http.GET
import com.example.feature_main_screen.data.remote.responce.RocketResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

const val baseUrl = "https://api.spacexdata.com/"


interface ApiRockets {

    @GET("./v4/rockets")
    suspend fun getRocketArrayList(): Response<RocketResponse>

}