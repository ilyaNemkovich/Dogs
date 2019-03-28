package com.example.dogs.data.network

import com.example.dogs.dto.RandomImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET(value = "api/breeds/image/random/10")
    fun getRandomImage(): Single<RandomImageResponse>

    @GET(value = "https://dog.ceo/api/breed/{breed}/images")
    fun getImageByBreed(@Path("breed") breed: String): Single<RandomImageResponse>
}