package com.example.feature_main_screen.di

import dagger.Module
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.example.feature_main_screen.domain.repository.RocketRepo
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetDataUseCase(rocketRepo: RocketRepo): GetDataUseCase {
        return GetDataUseCase(rocketRepo)
    }
}