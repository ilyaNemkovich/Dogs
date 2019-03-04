package com.example.dogs.ui.fragment.randomDogImage

import android.databinding.ObservableField
import com.example.dogs.data.network.DogApi
import com.example.dogs.dto.RandomImageResponse
import com.example.dogs.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomDogImageViewModel @Inject constructor(private val dogApi: DogApi) : BaseViewModel() {
    val urlString = ObservableField<RandomImageResponse>()

    init {
        loadRandomUrl()
    }

    fun loadRandomUrl() {
        dogApi.getRandomImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                urlString.set(response)
            }.let(compositeDisposable::add)
    }
}