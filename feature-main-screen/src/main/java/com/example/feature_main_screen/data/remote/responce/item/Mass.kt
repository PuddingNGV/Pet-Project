package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.MassLocal
import com.example.feature_main_screen.domain.models.Mass

data class Mass(
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int
) {
    fun toLocalMass(): MassLocal = MassLocal(kg, lb)
    fun toModelsMass(): Mass = Mass(kg, lb)
}