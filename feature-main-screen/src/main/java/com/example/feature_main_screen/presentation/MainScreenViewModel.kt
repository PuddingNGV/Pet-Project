package com.example.feature_main_screen.presentation


import androidx.lifecycle.*
import com.example.feature_main_screen.domain.usecase.GetDataUseCase
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    private var countImage = 0

    val rocket = getDataUseCase.executeLocal().asLiveData()

    fun switchImage(imageList: List<String>): RequestCreator {

        fun picassoRequestCreate(int: Int): RequestCreator {
            return Picasso.get()
                .load(imageList[int])
        }

        fun nextImage(): RequestCreator {
            if (countImage !in imageList.indices) {
                countImage = 0
            }

            return when (countImage) {
                in imageList.indices -> {
                    val picassoRequest = picassoRequestCreate(countImage)
                    countImage += 1
                    picassoRequest
                }
                else -> picassoRequestCreate(countImage)
            }
        }
        return nextImage()
    }

}
