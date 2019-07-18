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
            }, {

            }).let(compositeDisposable::add)
    }

//    fun loadImageByBreed(breed: String) {
////        never used
//        dogApi.getImageByBreed(breed)
//            .flatMap {
//                Single.just(DogBreeds(breed, it.message!!))
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { response ->
//                mutableBreadImagesResponse.postValue(response)
//            }
//            .let(compositeDisposable::add)
//    }
}