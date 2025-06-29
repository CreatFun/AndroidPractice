package com.example.androidpractice.favourite.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.androidpractice.favourite.presentation.viewModel.FavouriteViewModel
import com.example.androidpractice.listWithDetails.presentation.screens.MovieItem
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel


@Parcelize
class FavouriteScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val viewModel = koinViewModel<FavouriteViewModel>()
        val state by viewModel.viewState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Избранное") })
            },
            floatingActionButton = {
                Button(onClick = { viewModel.onUpdateClick() }) {
                    Text("Обновить")
                }
            }
        ) { innerPadding ->
            LazyColumn(Modifier.padding(innerPadding)) {
                items(state.items) {
                    MovieItem(item = it)
                }
            }
        }
    }
}