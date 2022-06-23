package com.example.feature_main_screen.di

import com.example.feature_main_screen.data.remote.RocketRepoImpl
import com.example.feature_main_screen.domain.repository.RocketRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRocketRepo(): RocketRepo {
        return RocketRepoImpl()
    }
}