package com.example.dogs.model.data.network

import com.example.dogs.model.data.dto.RandomImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET(value = "api/breeds/image/random/{listSize}")
    fun getRandomImageList(@Path("listSize") listSize: Int): Single<RandomImageResponse>

    @GET(value = "https://dog.ceo/api/breed/{breed}/images")
    fun getImageByBreed(@Path("breed") breed: String): Single<RandomImageResponse>
}