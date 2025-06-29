package com.example.androidpractice.listWithDetails.data.repository

import com.example.androidpractice.listWithDetails.data.API.MovieAPI
import com.example.androidpractice.listWithDetails.data.database.MovieDatabase
import com.example.androidpractice.listWithDetails.data.entity.MovieDbEntity
import com.example.androidpractice.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.example.androidpractice.listWithDetails.domain.entity.MovieType
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieAPI,
    private val mapper: MovieResponseToEntityMapper,

    private val db: MovieDatabase

): IMoviesRepository {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO){
            val response = api.getMovies(q)
            mapper.mapSearch(response)
        }


    override suspend fun getById(id: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.mapFull(response)
        }

    override suspend fun saveFavorite(movie: MoviesShortEntity) =
        withContext(Dispatchers.IO) {
            db.movieDao().insert(
                MovieDbEntity(
                    movieName = movie.primary_title,
                    movieYear = movie.start_year,
                    movieType = movie.type.name,
                    movieImgUrl = movie.primary_image
                )
            )
        }

    override suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.movieDao().getAll().map {
                MoviesShortEntity(
                    id = it.id.toString(),
                    type = MovieType.getByValue(it.movieType),
                    primary_title = it.movieName.orEmpty(),
                    start_year = it.movieYear.orEmpty(),
                    primary_image = it.movieImgUrl.orEmpty()
                )
            }
        }

}