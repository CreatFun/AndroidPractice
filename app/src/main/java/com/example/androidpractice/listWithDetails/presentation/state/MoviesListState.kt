package com.example.androidpractice.listWithDetails.presentation.state

import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity

interface MoviesListState {
    val items: List<MoviesShortEntity>
    val query: String
    val isEmpty: Boolean
    var isLoading: Boolean
    val error: String?
}