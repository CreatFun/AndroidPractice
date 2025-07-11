package com.example.androidpractice.listWithDetails.presentation.state

import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity

interface MovieDetailsState {
    val movie: MovieFullEntity?
    val isLoading: Boolean
    val error: String?
}