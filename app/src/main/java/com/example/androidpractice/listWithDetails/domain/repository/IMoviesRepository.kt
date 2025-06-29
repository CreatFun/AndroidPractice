package com.example.androidpractice.listWithDetails.domain.repository

import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity

interface IMoviesRepository {
    suspend fun getList(q: String = ""): List<MoviesShortEntity>

    suspend fun getById(id: String): MovieFullEntity?
    suspend fun getFavorites(): List<MoviesShortEntity>
    suspend fun saveFavorite(movie: MoviesShortEntity)
}