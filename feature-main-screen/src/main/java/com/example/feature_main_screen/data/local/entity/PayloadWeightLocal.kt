package com.example.feature_main_screen.data.local.entity

import androidx.room.ColumnInfo
import com.example.feature_main_screen.domain.models.Payload

data class PayloadWeightLocal(
    @ColumnInfo(name = "payload_id")val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
) {
    fun toModelsPayload(): Payload = Payload(kg, lb)
}