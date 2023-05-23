package com.gvstang.dicoding.learning.favouriteartist.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gvstang.dicoding.learning.favouriteartist.R
import com.gvstang.dicoding.learning.favouriteartist.data.Injection
import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.ui.ViewModelFactory
import com.gvstang.dicoding.learning.favouriteartist.ui.common.UiState
import com.gvstang.dicoding.learning.favouriteartist.ui.component.ArtistItem
import com.gvstang.dicoding.learning.favouriteartist.ui.component.SearchBar
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAllArtists()
            }
            is UiState.Success -> {
                HomeContent(
                    listArtists = uiState.data,
                    query = query,
                    onQueryChanged = viewModel::search,
                    modifier = modifier,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    listArtists: List<Artist>,
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            SearchBar(
                query = query,
                onQueryChanged = onQueryChanged
            )
        }
        if(listArtists.isNotEmpty()) {
            items(listArtists) { artist ->
                ArtistItem(
                    image = artist.image,
                    name = artist.name,
                    modifier = modifier.clickable {
                        Log.d("artist", artist.name)
                    }
                )
            }
        } else {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.error_not_found),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    FavouriteArtistTheme {
        HomeContent(
            listArtists = listOf<Artist>(),
            query = "",
            onQueryChanged = {}
        )
    }
}