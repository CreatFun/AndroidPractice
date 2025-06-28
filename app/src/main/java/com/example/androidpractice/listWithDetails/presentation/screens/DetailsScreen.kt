package com.example.androidpractice.listWithDetails.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractice.listWithDetails.data.repository.MoviesRepository
import com.example.androidpractice.listWithDetails.presentation.state.MovieDetailsState
import com.example.androidpractice.listWithDetails.presentation.viewModel.MovieDetailsViewModel
import com.example.androidpractice.ui.components.FullscreenLoading
import com.example.androidpractice.ui.components.FullscreenMessage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import ru.dekabrsky.consecutivepractice2025.ui.theme.Spacing
import com.github.terrakok.modo.stack.LocalStackNavigation
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Parcelize
class DetailsScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
    val movieId: String
): Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<MovieDetailsViewModel>{
            parametersOf(navigation, movieId)
        }
        val state = viewModel.viewState

        MovieScreenContent(
            state,
            onBackPressed = { viewModel.back() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieScreenContent(
    state: MovieDetailsState,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    state.movie?.primary_title.orEmpty()
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
            )
        },
    ) {
        if (state.isLoading) {
            FullscreenLoading()
            return@Scaffold
        }

        state.error?.let {
            FullscreenMessage(msg = it)
            return@Scaffold
        }
        val movie = state.movie ?:
            return@Scaffold


        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)
                .verticalScroll(state = rememberScrollState(), enabled = true)) {


            Column() {

                AsyncImage(
                    model = movie.primary_image,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxHeight()
                        .height(400.dp)
                        .width(270.dp))


                Spacer(modifier = Modifier.height(Spacing.medium))

                Text(
                    text = movie.primary_title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "${movie.start_year} • ${stringResource(movie.type.stringRes)}",
                    style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(Spacing.medium))

                Row {
                    Text(
                        text = "Длительность ${movie.runtime_minutes} мин",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.7F)
                    )

                    Text(
                        text = "Рейтинг ${movie.aggregate_rating}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.3F)
                    )
                    Text(
                        text = "Оценок: ${movie.votes_count}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.3F)
                    )
                }


                Spacer(modifier = Modifier.height(Spacing.medium))

                Text(
                    text = movie.plot,
                    style = MaterialTheme.typography.bodyLarge
                )
            }




//            Spacer(modifier = Modifier.height(Spacing.medium))
//
//            RatingBar(
//                rating = state.rating,
//                onRatingChanged = { onRatingChanged.invoke(it) },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//
//            Spacer(modifier = Modifier.height(Spacing.small))
//
//            if (state.isRatingVisible) {
//                Text(
//                    text = "Ваша оценка ${state.rating}/5",
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
        }
    }



}

//@Preview
//@Composable
//private fun MovieScreenContentPreview() {
//    MovieScreenContent(object : MovieDetailsState {
//        override val movie = MoviesRepository().getById("tt12299608")
//    }, {})
//
//}

@Composable
fun EmptyDataBox(msg: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = msg)
    }
}