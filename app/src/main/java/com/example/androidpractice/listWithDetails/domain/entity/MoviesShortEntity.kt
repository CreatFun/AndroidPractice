package com.example.androidpractice.listWithDetails.domain.entity

class MoviesShortEntity(
    val id: String,
    val type: String,
    val primary_title: String,
    val start_year: String,
    //TODO: end_year
    val primary_image: String
)
//TODO: Сделать перевод типа (фильм, сериал, минисериал)