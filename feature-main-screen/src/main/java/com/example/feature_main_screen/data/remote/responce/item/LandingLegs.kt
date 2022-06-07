package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName

data class LandingLegs(
    @SerializedName("material")
    val material: String,
    @SerializedName("number")
    val number: Int
)