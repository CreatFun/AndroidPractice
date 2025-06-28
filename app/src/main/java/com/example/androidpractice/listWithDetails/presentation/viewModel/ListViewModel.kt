package com.example.androidpractice.listWithDetails.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.coroutinesUtils.launchLoadingAndError
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.screens.DetailsScreen
import com.example.androidpractice.listWithDetails.presentation.state.MoviesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    private val textChangesFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadMovies(it) }
        }
    }

    private fun loadMovies(query: String) {
        mutableState.items = emptyList()
        //TODO: mutableState.error = null

        if (query.length < MIN_QUERY_LENGTH_TO_SEARCH) return

        viewModelScope.launchLoadingAndError(
            //TODO: handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.items = repository.getList(query)
        }

////        mutableState.items = repository.getList(viewState.query)
//        mutableState.items = emptyList()
////        mutableState.error = null
//        viewModelScope.launchLoadingAndError(
////            handleError = { mutableState.error = it.localizedMessage },
//            updateLoading = { mutableState.isLoading = it },
////            handleError = TODO()
//        ) {
//            mutableState.items = repository.getList(query)
//        }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }


    private class MutableMoviesListState: MoviesListState {
        override var items: List<MoviesShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        //TODO: override var error: String? by mutableStateOf(null)
    }
    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
    }
}