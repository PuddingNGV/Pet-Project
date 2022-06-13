package com.example.feature_main_screen.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_main_screen.data.remote.RocketRepoImpl
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.example.feature_main_screen.data.remote.ApiHelper

class MainScreenViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val rocketRepo by lazy { RocketRepoImpl(context) }

    private val getDataUseCase by lazy { GetDataUseCase(rocketRepo) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(
            getDataUseCase = getDataUseCase
        ) as T
    }
}