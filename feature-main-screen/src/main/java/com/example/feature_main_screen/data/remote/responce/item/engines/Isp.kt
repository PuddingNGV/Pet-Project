package com.example.feature_main_screen.data.remote.responce.item.engines


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.engines.IspLocal

data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("vacuum")
    val vacuum: Int
)
{
    fun toLocalIsp():IspLocal = IspLocal(seaLevel, vacuum)
}