package com.example.dogs.ui.fragment.randomDogImage

import androidx.lifecycle.MutableLiveData
import com.example.dogs.model.repository.DogsRepository
import com.example.dogs.ui.base.BaseViewModel
import com.example.dogs.ui.fragment.randomDogImage.data.DogBreeds
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomDogImageViewModel @Inject constructor(
    private val dogsRepository: DogsRepository
) : BaseViewModel() {
    val mutableRandomImageResponse = MutableLiveData<List<RandomImageQuiz>>()
    val mutableBreadImagesResponse = MutableLiveData<DogBreeds>()
    val mutableCurrentImage = MutableLiveData<String>()

    var wrongAnswers = 0
    var rightAnswers = 0

    init {
        loadRandomUrl()
    }

    fun loadRandomUrl() {
        dogsRepository.getQuizeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                mutableRandomImageResponse.postValue(response)
                mutableCurrentImage.postValue(response.first { it.isCorrectAnswer }.imageUrl)
                getBreadImages(response.first { it.isCorrectAnswer }.breed)
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)
    }

    fun getBreadImages(bread: String) {
        dogsRepository.loadImageByBreed(bread)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.imageUrls.isEmpty()) {
                    mutableCurrentImage.postValue(null)
                } else {
                    mutableBreadImagesResponse.postValue(response)
                }
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)
    }

    fun changeImage() {
        mutableBreadImagesResponse.value?.apply {
            mutableCurrentImage.postValue(this.imageUrls.random())
        }
    }
}