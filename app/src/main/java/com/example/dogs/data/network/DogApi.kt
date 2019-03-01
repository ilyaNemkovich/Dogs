package com.example.dogs.data.network

import com.example.dogs.dto.RandomImageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DogApi {
    @GET(value = "api/breeds/image/random")
    fun getRandomImage(): Single<RandomImageResponse>
}