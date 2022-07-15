package com.example.feature_main_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.feature_main_screen.data.local.entity.*

@Database(entities = [RocketDbEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppRocketDataBase : RoomDatabase() {
    abstract fun getRocketDao(): RocketDao
}