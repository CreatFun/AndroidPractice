package com.example.androidpractice.listWithDetails.domain.entity

import androidx.annotation.StringRes
import com.example.androidpractice.R

class MoviesShortEntity(
    val id: String,
    val type: MovieType,
    val primary_title: String,
    val start_year: String,
    val primary_image: String
)
//TODO: Пофиксить отображение
enum class MovieType(@StringRes val stringRes: Int) {
    MOVIE(R.string.movie),
    SERIES(R.string.tvSeries),
    MINI_SERIES(R.string.tvMiniSeries),
    OTHER(R.string.other);

    companion object {
        fun getByValue(type: String?) = entries.find { it.name.equals(type, ignoreCase = true) } ?: SERIES
    }
}