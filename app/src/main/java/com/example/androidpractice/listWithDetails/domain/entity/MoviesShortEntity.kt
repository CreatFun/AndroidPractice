package com.example.androidpractice.listWithDetails.domain.entity

class MoviesShortEntity(
    val id: String,
    val type: String,
    val primary_title: String,
    val original_title: String,
    val aggregate_rating: Double,
    val start_year: String,
    //TODO: end_year
    val img_url: String
)