package com.example.androidpractice.listWithDetails.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class MovieFullResponse(
    val id: String?,
    @SerializedName("primary_title")
    val primary_title: String?,
    @SerializedName("start_year")
    val start_year: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("plot")
    val plot: String?,
    @SerializedName("primary_image")
    val primary_image: ImgURLResponse?,
    @SerializedName("rating")
    val rating: RatingResponse,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("runtime_minutes")
    val runtime_minutes: String?
): MovieApiBaseResponse()

class RatingResponse(
    @SerializedName("aggregate_rating")
    val aggregate_rating: String?,
    @SerializedName("votes_count")
    val votes_count: String?
)