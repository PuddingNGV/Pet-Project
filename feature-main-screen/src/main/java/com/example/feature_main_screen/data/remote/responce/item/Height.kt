package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.HeightLocal
import com.example.feature_main_screen.domain.models.Height

data class Height(
    @SerializedName("feet")
    val feet: Double,
    @SerializedName("meters")
    val meters: Double
) {
    fun toLocalHeight(): HeightLocal = HeightLocal(meters, feet)
    fun toModelsHeight(): Height = Height(meters, feet)
}