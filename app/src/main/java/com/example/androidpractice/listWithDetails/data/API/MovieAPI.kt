package com.example.androidpractice.listWithDetails.data.API

import com.example.androidpractice.listWithDetails.data.model.MovieFullResponse
import com.example.androidpractice.listWithDetails.data.model.MoviesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("/v2/search/titles")
    suspend fun getMovies(
        @Query("query") search: String,

        //TODO:Чтобы использовать фильтры нужно добавить подобный параметр, но в IMDbAPI нет рабочих query для фильтрации списка фильмов/сериаов
        //@Query("type") type: String?
    ) : MoviesSearchResponse

    @GET("/v2/titles/{title_id}")
    suspend fun getMovie(
        @Path("title_id") id: String? = null
//        @Query("i") id: String? = null,
    ) : MovieFullResponse
}