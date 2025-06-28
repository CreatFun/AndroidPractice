package com.example.androidpractice.listWithDetails.data.API

import com.example.androidpractice.listWithDetails.data.model.MovieFullResponse
import com.example.androidpractice.listWithDetails.data.model.MoviesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("page") page: Int = 1
    ) : MoviesSearchResponse

    @GET("/")
    suspend fun getMovie(
        @Query("i") id: String? = null,
    ) : MovieFullResponse
}