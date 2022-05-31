package com.example.feature_main_screen.data.responce.item


import com.google.gson.annotations.SerializedName

data class Mass(
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int
)