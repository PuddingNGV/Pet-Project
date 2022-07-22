package com.example.feature_main_screen.data.remote.responce.item.stage.second


import com.google.gson.annotations.SerializedName

data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: DiameterX,
    @SerializedName("height")
    val height: HeightX
)