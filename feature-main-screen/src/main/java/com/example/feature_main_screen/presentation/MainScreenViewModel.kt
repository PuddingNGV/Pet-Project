package com.example.feature_main_screen.presentation


import androidx.lifecycle.*
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import kotlinx.coroutines.*


class MainScreenViewModel(private val getDataUseCase: GetDataUseCase): ViewModel() {



    private var dataRocketPackMutable = MutableLiveData<RocketInfo>()

    var dataRocketPackLive:LiveData<RocketInfo> = dataRocketPackMutable



    fun getData() {

        viewModelScope.launch {
            dataRocketPackMutable.value= getDataUseCase.execute()
            println("${dataRocketPackLive.value} FUCK MEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
        }

    }




/*
    fun login(name: String, password: String) {
        runCoroutine(dataRocketPackMutable) {
            val response = ServerApi.restApi.authorize(name, password).execute()
            return@runCoroutine response.body()!!
        }
    }

    protected fun <T> runCoroutine(correspondenceLiveData: MutableLiveData<Resource<T>>, block: suspend () -> T) {
        correspondenceLiveData.value = Resource(Resource.Status.LOADING, null, null)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val result = block()
                correspondenceLiveData.postValue(Resource(Resource.Status.COMPLETED, result, null))
            } catch (exception: Exception) {
                val error = ErrorConverter.convertError(exception)
                correspondenceLiveData.postValue(Resource(Resource.Status.COMPLETED, null, error))
            }
        }
    }
    */
}
