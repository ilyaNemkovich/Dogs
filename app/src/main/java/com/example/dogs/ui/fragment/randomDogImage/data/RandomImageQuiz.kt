package com.example.dogs.ui.fragment.randomDogImage.data

data class RandomImageQuiz(
    val imageUrl: String,
    var isCorrectAnswer: Boolean,
    val breed: String
)