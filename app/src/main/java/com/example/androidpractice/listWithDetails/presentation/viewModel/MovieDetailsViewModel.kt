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
            //TODO: handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.movie = repository.getById(id)

            //TODO: "Похожие фильмы"
//            mutableState.movie?.primary_title?.let {
//                launch { mutableState.related = repository.getList(it).take(3) }
//            }
        }
//        mutableState.movie = repository.getById(id)
    }

    fun back() {
        navigation.back()
    }

    //TODO: для пользовательского рейтинга (можно убрать, если не буду реализовывать)
//    fun onRatingChanged(rating: Float) {
//        mutableState.rating = rating
//    }

    private class MutableDetailsState : MovieDetailsState {
        override var movie: MovieFullEntity? by mutableStateOf(null)
        override var isLoading: Boolean by mutableStateOf(false)
        //TODO: override var error: String? by mutableStateOf(null)

        //TODO: для пользовательского рейтинга (можно убрать, если не буду реализовывать)
//        override var rating: Float by mutableFloatStateOf(0f)
//        override val isRatingVisible: Boolean get() = rating != 0f
        //TODO: "Похожие фильмы"
        //override var related: List<MoviesShortEntity> by mutableStateOf(emptyList())
    }
}