package com.example.androidpractice.listWithDetails.data.mapper

import com.example.androidpractice.listWithDetails.data.model.MovieFullResponse
import com.example.androidpractice.listWithDetails.data.model.MoviesSearchResponse
import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity
import com.example.androidpractice.listWithDetails.domain.entity.MovieType
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity

class MovieResponseToEntityMapper {
    fun mapSearch(response: MoviesSearchResponse) =
        response.titles?.map { movie ->
            MoviesShortEntity(
                id = movie.id.orEmpty(),
                primary_title = movie.primary_title.orEmpty(),
                type = MovieType.getByValue(movie.type),
                start_year = movie.start_year.orEmpty(),
                primary_image = movie.primary_image?.url.orEmpty()
            )
        }.orEmpty()

    fun mapFull(response: MovieFullResponse) =
        MovieFullEntity(
            primary_title = response.primary_title.orEmpty(),
            id = response.id.orEmpty(),
            start_year = response.start_year.orEmpty(),
            plot = response.plot.orEmpty(),
            primary_image = response.primary_image?.url.orEmpty(),
            aggregate_rating = response.rating.aggregate_rating.orEmpty(),
            votes_count = response.rating.votes_count.orEmpty(),
            type = MovieType.getByValue(response.type),
            genres = response.genres.orEmpty(),
            runtime_minutes = response.runtime_minutes.orEmpty()
        )
}