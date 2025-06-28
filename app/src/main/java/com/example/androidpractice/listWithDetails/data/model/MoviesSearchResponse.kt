package com.example.androidpractice.listWithDetails.data.model

import com.google.gson.annotations.SerializedName

class MoviesSearchResponse(
    @SerializedName("titles")
    val titles: List<MoviesShortResponse>?
): MovieApiBaseResponse()

class MoviesShortResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("primary_title")
    val primary_title: String?,
    @SerializedName("start_year")
    val start_year: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("primary_image")
    val primary_image: String?
)