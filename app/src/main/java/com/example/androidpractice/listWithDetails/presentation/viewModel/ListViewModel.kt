package com.example.androidpractice.listWithDetails.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.coroutinesUtils.launchLoadingAndError
import com.example.androidpractice.listWithDetails.domain.entity.MovieType
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.screens.DetailsScreen
import com.example.androidpractice.listWithDetails.presentation.state.MoviesListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import org.koin.java.KoinJavaComponent.inject
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    private val textChangesFlow = MutableStateFlow("")

    private var filterTypes: Set<MovieType> = emptySet()

    private val dataStore: DataStore<Preferences> by inject(DataStore::class.java)
    private val typesKey = stringSetPreferencesKey(KEY_MOVIE_TYPES)

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadMovies() }
        }
        mutableState.typesVariants = setOf(
            MovieType.MOVIE,
            MovieType.SERIES,
            MovieType.MINI_SERIES)

        viewModelScope.launch {
            dataStore.data.collect {
                filterTypes = it[typesKey]
                    ?.map { MovieType.getByValue(it) }
                    ?.toSet()
                    .orEmpty()
                updateBadge()
            }
        }
    }

    private fun loadMovies() {
        val query = textChangesFlow.value

        mutableState.items = emptyList()
        mutableState.error = null

        if (query.length < MIN_QUERY_LENGTH_TO_SEARCH) return

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.items = repository.getList(query)
        }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onSelectionDialogDismissed() {
        mutableState.showTypesDialog = false
    }

    fun onSelectedVariantChanged(variant: MovieType, selected: Boolean) {
        mutableState.selectedTypes = mutableState.selectedTypes.run {
            if (selected) plus(variant) else minus(variant)
        }
    }

    fun onFiltersConfirmed() {
        if (filterTypes != mutableState.selectedTypes){
            filterTypes = mutableState.selectedTypes
            loadMovies()
            updateBadge()

            viewModelScope.launch {
                dataStore.edit {
                    it[typesKey] = filterTypes.map { it.name }.toSet()
                }
            }
        }
        onSelectionDialogDismissed()
    }

    private fun updateBadge(){
        mutableState.hasBadge = filterTypes.isNotEmpty()
    }

    fun onFiltersClicked(){
        mutableState.showTypesDialog = true
        mutableState.selectedTypes = filterTypes
    }


    private class MutableMoviesListState: MoviesListState {
        override var items: List<MoviesShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var hasBadge: Boolean by mutableStateOf(false)
        override var showTypesDialog: Boolean by mutableStateOf(false)
        override var typesVariants: Set<MovieType> by mutableStateOf(emptySet())
        override var selectedTypes: Set<MovieType> by mutableStateOf(emptySet())
    }
    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
        private const val KEY_MOVIE_TYPES = "MOVIE_TYPES"
    }
}