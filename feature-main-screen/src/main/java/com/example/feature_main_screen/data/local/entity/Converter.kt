package com.example.feature_main_screen.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converter {

    @TypeConverter
    fun stringToListStage(data: String?): List<StageLocal?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<StageLocal?>?>() {}.type
        return gson.fromJson<List<StageLocal?>>(data, listType)
    }

    @TypeConverter
    fun listStageToString(someObjects: List<StageLocal?>?): String? {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToListPayloadWeight(data: String?): List<PayloadWeightLocal?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<PayloadWeightLocal?>?>() {}.type
        return gson.fromJson<List<PayloadWeightLocal?>>(data, listType)
    }

    @TypeConverter
    fun listPayloadWeightToString(someObjects: List<PayloadWeightLocal?>?): String? {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToListFlickrImage(data: String?): List<String?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String?>>(data, listType)
    }

    @TypeConverter
    fun listFlickrImageToString(someObjects: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(someObjects)
    }
}