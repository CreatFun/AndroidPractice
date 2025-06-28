package com.example.androidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.screens.DetailsScreen
import com.example.androidpractice.listWithDetails.presentation.state.MoviesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward

class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    //TODO: для поисковой строки (можно использовать для фильтрации)
//    fun onQueryChanged(query: String) {
//        mutableState.query = query
//        loadMovies()
//    }


    private class MutableMoviesListState: MoviesListState {
        override var items: List<MoviesShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
    }
}