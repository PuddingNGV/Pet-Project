package com.example.feature_main_screen.data.remote.responce.item


import com.google.gson.annotations.SerializedName
import com.example.feature_main_screen.data.local.entity.PayloadWeightLocal
import com.example.feature_main_screen.domain.models.Payload

data class PayloadWeight(
    @SerializedName("id")
    val id: String,
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int,
    @SerializedName("name")
    val name: String
) {
    fun toLocalPayloadWeight(): PayloadWeightLocal = PayloadWeightLocal(id, name, kg, lb)
    fun toModelsPayload(): Payload = Payload(kg, lb)
}