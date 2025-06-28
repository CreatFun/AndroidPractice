package com.example.androidpractice.listWithDetails.presentation.state

import com.example.androidpractice.listWithDetails.domain.entity.MovieType
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity

interface MoviesListState {
    val items: List<MoviesShortEntity>
    val query: String
    val isEmpty: Boolean
    var isLoading: Boolean
    val error: String?
    val hasBadge: Boolean
    val showTypesDialog: Boolean
    val typesVariants: Set<MovieType>
    val selectedTypes: Set<MovieType>
}