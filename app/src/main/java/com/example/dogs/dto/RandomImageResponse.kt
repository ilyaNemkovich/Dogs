package com.example.dogs.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RandomImageResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("message")
    @Expose
    var message: List<String>? = null
}
