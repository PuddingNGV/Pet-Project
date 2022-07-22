package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.LandingLegsLocal

data class LandingLegs(
    @SerializedName("material")
    val material: String?,
    @SerializedName("number")
    val number: Int
) {
    fun toLocalLandingLegs():LandingLegsLocal = LandingLegsLocal(number, let{material.orEmpty()})
}