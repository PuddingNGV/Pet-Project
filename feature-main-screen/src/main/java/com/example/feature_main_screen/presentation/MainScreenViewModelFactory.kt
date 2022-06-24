package com.example.feature_main_screen.presentation

/*
class MainScreenViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val rocketRepo by lazy { RocketRepoImpl(context) }
    private val getDataUseCase by lazy { GetDataUseCase(rocketRepo) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(
            getDataUseCase = getDataUseCase
        ) as T
    }
}

 */