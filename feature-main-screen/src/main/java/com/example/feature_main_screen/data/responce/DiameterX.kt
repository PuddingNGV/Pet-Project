package com.example.feature_main_screen.data.responce


import com.google.gson.annotations.SerializedName

data class DiameterX(
    @SerializedName("feet")
    val feet: Any?,
    @SerializedName("meters")
    val meters: Any?
)