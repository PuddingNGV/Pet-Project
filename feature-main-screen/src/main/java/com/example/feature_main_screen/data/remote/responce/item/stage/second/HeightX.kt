package com.example.feature_main_screen.data.remote.responce.item.stage.second


import com.google.gson.annotations.SerializedName

data class HeightX(
    @SerializedName("feet")
    val feet: Any?,
    @SerializedName("meters")
    val meters: Any?
)