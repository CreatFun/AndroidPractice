package com.example.androidpractice.favourite.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.favourite.presentation.state.FavouriteViewState
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val repository: IMoviesRepository
): ViewModel() {


    private var mutableState = MutableStateFlow(FavouriteViewState())
    val viewState = mutableState.asStateFlow()

    init {
        updateFavorites()
    }
    fun onUpdateClick() {
        updateFavorites()
    }

    private fun updateFavorites() {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}