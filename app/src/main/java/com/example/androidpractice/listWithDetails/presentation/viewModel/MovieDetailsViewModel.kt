package com.example.androidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.coroutinesUtils.launchLoadingAndError
import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.state.MovieDetailsState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {

    private val mutableState = MutableDetailsState()
    val viewState = mutableState as MovieDetailsState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.movie = repository.getById(id)

        }
    }

    fun back() {
        navigation.back()
    }



    private class MutableDetailsState : MovieDetailsState {
        override var movie: MovieFullEntity? by mutableStateOf(null)
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}