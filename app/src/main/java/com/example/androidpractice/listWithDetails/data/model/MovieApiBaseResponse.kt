package com.example.androidpractice.listWithDetails.data.model

import com.google.gson.annotations.SerializedName

abstract class MovieApiBaseResponse {
    @SerializedName("Response")
    val response: Boolean = false

    @SerializedName("Error")
    val error: String? = null
}