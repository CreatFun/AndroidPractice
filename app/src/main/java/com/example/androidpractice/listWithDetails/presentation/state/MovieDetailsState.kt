package com.example.androidpractice.listWithDetails.presentation.state

import com.example.androidpractice.listWithDetails.data.entity.MovieFullEntity

interface MovieDetailsState {
    val movie: MovieFullEntity?
}