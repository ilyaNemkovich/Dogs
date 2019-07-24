package com.example.dogs.model.repository

import com.example.dogs.model.data.network.DogApi
import com.example.dogs.ui.fragment.randomDogImage.data.DogBreeds
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz
import com.example.dogs.ui.utils.fromUrlToBreed
import io.reactivex.Single
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val dogApi: DogApi
) {
    fun getQuizeList(listSize: Int = 4): Single<List<RandomImageQuiz>> {
        return dogApi.getRandomImageList(listSize + 5)
            .flatMap {
                val quiz: MutableList<RandomImageQuiz> = mutableListOf()
                it.message?.let { list ->
                    do {
                        if (quiz.isNotEmpty()) {
                            for (breads in quiz) {
                                if (breads.breed != fromUrlToBreed(list[quiz.size + 1])) {
                                    quiz.add(
                                        RandomImageQuiz(
                                            list[quiz.size + 1],
                                            false,
                                            fromUrlToBreed(list[quiz.size + 1])
                                        )
                                    )
                                    break
                                }
                            }
                        } else {
                            quiz.add(RandomImageQuiz(list.first(), false, fromUrlToBreed(list.first())))
                        }
                    } while (quiz.size < listSize)
                }
                quiz.random().isCorrectAnswer = true
                Single.just(quiz)
            }
    }

    fun loadImageByBreed(breed: String): Single<DogBreeds> {
        val breadQ = breed.replace("-", "/")
        val url = "https://dog.ceo/api/breed/$breadQ/images"
        return dogApi.getImageByBreed(url)
            .flatMap {
                Single.just(DogBreeds(breed, it.message!!))
            }
    }
}