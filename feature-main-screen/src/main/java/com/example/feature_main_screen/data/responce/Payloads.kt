package com.example.feature_main_screen.data.responce


import com.google.gson.annotations.SerializedName

data class Payloads(
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairing,
    @SerializedName("option_1")
    val option1: String
)