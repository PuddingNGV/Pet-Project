package com.example.feature_main_screen.presentation


import androidx.lifecycle.*
import com.example.feature_main_screen.domain.models.RocketInfo
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.feature_main_screen.until.Resource
import com.example.feature_main_screen.data.RocketRepoImpl
import com.example.feature_main_screen.data.local.entity.RocketDbEntity

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    private var intImage = 0

    /*
        init {
            viewModelScope.launch(Dispatchers.Main) {
                getData2()
            }
        }

            private var dataRocketPackMutable = MutableLiveData<RocketInfo>()
            var dataRocketPackLive: LiveData<RocketInfo> = dataRocketPackMutable
            // dataRocketPackLive = dataRocketPackMutable

            private suspend fun getData() {
                    dataRocketPackMutable.value = getDataUseCase.executeRemote()
                    //dataRocketPackMutable.value = getDataUseCase.executeLocal().asLiveData()
            }
val rocket = liveData<Resource<List<RocketDbEntity>>>{
        getDataUseCase.executeLocal().asLiveData()
    }
         */
    val rocket = getDataUseCase.executeLocal().asLiveData()



    fun switchImage(imageList: List<String>): RequestCreator {

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
