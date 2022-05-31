package com.example.feature_main_screen.data.responce.item


import com.google.gson.annotations.SerializedName

data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("vacuum")
    val vacuum: Int
)