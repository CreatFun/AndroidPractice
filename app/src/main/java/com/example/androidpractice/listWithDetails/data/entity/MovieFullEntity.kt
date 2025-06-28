package com.example.androidpractice.listWithDetails.data.entity

class MovieFullEntity(
    val id: String,
    val type: String,
    val primary_title: String,
    val start_year: String,
    //TODO: end_year

    val image_url: String,
    val genres: List<String>,
    val rating: Double, //TODO: рейтинг и количество оценок
    val runtime_minutes: String,
    val plot: String
)


