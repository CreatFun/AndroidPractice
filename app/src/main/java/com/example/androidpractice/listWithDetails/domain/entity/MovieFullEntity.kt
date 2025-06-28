package com.example.androidpractice.listWithDetails.domain.entity

class MovieFullEntity(
    val id: String = "",
    val type: MovieType,
    val primary_title: String = "",
    val start_year: String = "",

    val primary_image: String = "",
    val genres: List<String> = emptyList(),
    val votes_count: String = "",
    val aggregate_rating: String = "",
    val runtime_minutes: String = "",
    val plot: String = ""
)


