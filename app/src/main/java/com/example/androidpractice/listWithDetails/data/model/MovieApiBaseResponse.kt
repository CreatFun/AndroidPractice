package com.example.androidpractice.listWithDetails.data.model

import com.google.gson.annotations.SerializedName

abstract class MovieApiBaseResponse {
    @SerializedName("code")
    val error_code: String? = null

    @SerializedName("message")
    val error_message: String? = null
}