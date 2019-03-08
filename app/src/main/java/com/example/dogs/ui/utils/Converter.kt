package com.example.dogs.ui.utils

fun fromUrlToBreed(url: String): String = url.substringBeforeLast("/").substringAfterLast("/")