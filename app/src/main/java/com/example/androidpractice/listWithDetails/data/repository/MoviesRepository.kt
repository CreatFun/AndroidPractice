package com.example.androidpractice.listWithDetails.data.repository

import com.example.androidpractice.listWithDetails.data.API.MovieAPI
import com.example.androidpractice.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieAPI,
    private val mapper: MovieResponseToEntityMapper
): IMoviesRepository {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO){
            val response = api.getMovies(q)
            if (response.response.not()) {
                throw Exception(response.error.orEmpty())
            }
            mapper.mapSearch(response)
        }


    override suspend fun getById(id: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            if (response.response.not()) {
                throw Exception(response.error.orEmpty())
            }
            mapper.mapFull(response)
        }}