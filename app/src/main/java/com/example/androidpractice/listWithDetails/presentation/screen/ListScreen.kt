package com.example.androidpractice.listWithDetails.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidpractice.listWithDetails.data.entity.MoviesShortEntity
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import androidx.compose.foundation.layout.Spacer
import coil3.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androidpractice.listWithDetails.data.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.data.mock.MoviesDataMock
import com.example.androidpractice.listWithDetails.repository.MoviesRepository
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import ru.dekabrsky.consecutivepractice2025.ui.theme.Spacing

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        var items by remember { mutableStateOf(MoviesRepository().getList()) }
        val navigation = LocalStackNavigation.current

        LazyColumn(Modifier.padding(Spacing.small)) {
            items(items) {
                MovieItem(
                    item = it,
                    Modifier.clickable { navigation.forward(DetailsScreen(movieId = it.id)) }
                )
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
            model = item.img_url,
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



@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(item = MoviesDataMock.moviesShort.first())
}

