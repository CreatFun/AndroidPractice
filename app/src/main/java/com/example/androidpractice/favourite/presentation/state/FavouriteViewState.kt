package com.example.androidpractice.favourite.presentation.state

import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity

data class FavouriteViewState(
    val items: List<MoviesShortEntity> = emptyList()
)