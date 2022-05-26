package com.example.feature_main_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_main_screen.data.RocketRepoImpl
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.usecase.GetDataUseCase

class MainScreenViewModel(private val getDataUseCase: GetDataUseCase): ViewModel() {


    private var dataRocketPackMutable = MutableLiveData<RocketInfo>()
    val dataRocketPackLive:LiveData<RocketInfo> = dataRocketPackMutable


    fun getData() {
        dataRocketPackMutable.value = getDataUseCase.execute()
    }
}