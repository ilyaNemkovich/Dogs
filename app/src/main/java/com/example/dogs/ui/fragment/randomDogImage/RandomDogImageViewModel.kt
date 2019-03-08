package com.example.dogs.ui.fragment.randomDogImage

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.dogs.data.network.DogApi
import com.example.dogs.ui.base.BaseViewModel
import com.example.dogs.ui.fragment.randomDogImage.data.DogBreeds
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz
import com.example.dogs.ui.utils.fromUrlToBreed
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomDogImageViewModel @Inject constructor(private val dogApi: DogApi) : BaseViewModel() {
    val randomImageResponse = ObservableField<List<RandomImageQuiz>>()
    val mutableRandomImageResponse = MutableLiveData<List<RandomImageQuiz>>()
    val mutableBreadImagesResponse = MutableLiveData<DogBreeds>()

    init {
        loadRandomUrl()
    }

    fun loadRandomUrl() {
        dogApi.getRandomImage()
            .flatMap {
                val quiz: MutableList<RandomImageQuiz> = mutableListOf()
                for (url in it.message!!) {
                    quiz.add(RandomImageQuiz(url, false, fromUrlToBreed(url)))
                }
                quiz[(0..3).random()].question = true
                Single.just(quiz)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                randomImageResponse.set(response)
                mutableRandomImageResponse.postValue(response)
            }.let(compositeDisposable::add)
    }

    fun loadImageByBreed(breed: String) {
//        never used
        dogApi.getImageByBreed(breed)
            .flatMap {
                Single.just(DogBreeds(breed, it.message!!))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                mutableBreadImagesResponse.postValue(response)
            }
            .let(compositeDisposable::add)
    }
}