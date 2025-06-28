package com.example.androidpractice.listWithDetails.presentation.state

import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity

interface MovieDetailsState {
    val movie: MovieFullEntity?
    val isLoading: Boolean
    //TODO: добавить поле ошибки val error: String? или Boolean
}