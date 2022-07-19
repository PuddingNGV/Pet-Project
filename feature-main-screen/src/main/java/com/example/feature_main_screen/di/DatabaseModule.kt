package com.example.feature_main_screen.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.feature_main_screen.data.local.AppRocketDataBase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppRocketDataBase =
        Room.databaseBuilder(app, AppRocketDataBase::class.java, "rocket_database")
            .build()

}