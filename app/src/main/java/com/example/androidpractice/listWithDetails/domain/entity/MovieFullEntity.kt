package com.example.androidpractice.listWithDetails.domain.entity

class MovieFullEntity(
    val id: String = "",
    val type: String = "",
    val primary_title: String = "",
    val start_year: String = "",
    //TODO: end_year

    val primary_image: String = "",
    val genres: List<String> = emptyList(),
    val rating: List<Rating> = emptyList(),
    val runtime_minutes: String = "",
    val plot: String = ""
) {
    class Rating(
        val votes_count: String = "",
        val aggregate_rating: String = ""
    )
}


