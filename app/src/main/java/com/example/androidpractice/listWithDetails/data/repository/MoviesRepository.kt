package com.example.androidpractice.listWithDetails.data.repository

import com.example.androidpractice.listWithDetails.data.mock.MoviesDataMock
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository

class MoviesRepository: IMoviesRepository {
    override fun getList(q: String) = MoviesDataMock.moviesShort.filter { it.primary_title.contains(q, ignoreCase = true) }

    override fun getById(id: String) = MoviesDataMock.moviesFull.find { it.id == id }
}