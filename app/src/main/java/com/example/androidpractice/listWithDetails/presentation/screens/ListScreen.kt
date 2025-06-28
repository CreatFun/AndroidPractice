package com.example.androidpractice.listWithDetails.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import coil3.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.res.stringResource
import com.example.androidpractice.R
import com.example.androidpractice.listWithDetails.presentation.viewModel.ListViewModel
import com.example.androidpractice.ui.components.FullscreenLoading
import com.example.androidpractice.ui.components.FullscreenMessage
import com.github.terrakok.modo.stack.LocalStackNavigation
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.ui.theme.Spacing

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
//        var items by remember { mutableStateOf(MoviesRepository().getList()) }

        val viewModel = koinViewModel<ListViewModel>{ parametersOf(navigation)}
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                TextField(
                    value = state.query,
                    onValueChange = { viewModel.onQueryChanged(it) },
                    label = { Text(stringResource(R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    leadingIcon = { Icon(Icons.Rounded.Search, null) }
                )
            },
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            if (state.isLoading) {
                FullscreenLoading()
                return@Scaffold
            }

            state.error?.let {
                FullscreenMessage(msg = it)
                return@Scaffold
            }

            if (state.isEmpty){
                EmptyDataBox("По запросу нет результатов")
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) {
                    MovieItem(
                        item = it,
                        Modifier.clickable { viewModel.onItemClicked(it.id) }
                    )
                }
            }
        }



    }
}


@Composable
fun MovieItem(
    item: MoviesShortEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(Spacing.medium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.primary_image,
            contentDescription = item.primary_title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column {
            Text(
                text = item.primary_title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${item.start_year} • ${item.type}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun MovieItemPreview() {
//    MovieItem(item = MoviesDataMock.moviesShort.first())
//}

