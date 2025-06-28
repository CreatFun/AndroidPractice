package com.example.androidpractice.listWithDetails.domain.repository

import com.example.androidpractice.listWithDetails.data.entity.MovieFullEntity
import com.example.androidpractice.listWithDetails.data.entity.MoviesShortEntity

interface IMoviesRepository {
    fun getList(q: String = ""): List<MoviesShortEntity>

    fun getById(id: String): MovieFullEntity?
}