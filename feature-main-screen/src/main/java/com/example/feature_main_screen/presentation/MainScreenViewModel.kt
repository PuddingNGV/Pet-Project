package com.example.feature_main_screen.presentation


import androidx.lifecycle.*
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.*

class MainScreenViewModel(private val getDataUseCase: GetDataUseCase): ViewModel() {

    private var intImage = 0

    init {
        getData()
    }

    private var dataRocketPackMutable = MutableLiveData<RocketInfo>()
    var dataRocketPackLive:LiveData<RocketInfo> = dataRocketPackMutable

    private fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            dataRocketPackMutable.value = getDataUseCase.execute()
        }
    }

    fun switchImage(imageList:List<String>): RequestCreator {

        fun picassoRequestCreate(int: Int): RequestCreator {
            return Picasso.get()
                .load(imageList[int])
        }

        fun nextImage(): RequestCreator {
            if (intImage !in imageList.indices) {
                intImage = 0
            }

            return when (intImage) {
                in imageList.indices -> {
                    val picassoRequest = picassoRequestCreate(intImage)
                    println(intImage)
                    intImage += 1
                    picassoRequest
                }
                else -> picassoRequestCreate(intImage)
            }
        }
        return nextImage()
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
