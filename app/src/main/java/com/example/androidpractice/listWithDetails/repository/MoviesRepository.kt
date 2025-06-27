package com.example.androidpractice.listWithDetails.repository

import com.example.androidpractice.listWithDetails.data.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.data.mock.MoviesDataMock

class MoviesRepository: IMoviesRepository {
    override fun getList(q: String) = MoviesDataMock.moviesShort.filter { it.primary_title.contains(q, ignoreCase = true) }

    override fun getById(id: String) = MoviesDataMock.moviesFull.find { it.id == id }
}