package com.example.feature_main_screen.data.responce.item


import com.google.gson.annotations.SerializedName

data class Diameter(
    @SerializedName("feet")
    val feet: Double,
    @SerializedName("meters")
    val meters: Double
)