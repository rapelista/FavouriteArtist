package com.gvstang.dicoding.learning.favouriteartist.ui.screen.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gvstang.dicoding.learning.favouriteartist.R
import com.gvstang.dicoding.learning.favouriteartist.data.Injection
import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.model.FakeArtistDataSource
import com.gvstang.dicoding.learning.favouriteartist.ui.ViewModelFactory
import com.gvstang.dicoding.learning.favouriteartist.ui.common.UiState
import com.gvstang.dicoding.learning.favouriteartist.ui.component.ArtistItem
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun FavouriteScreen(
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getFavouritedArtists()
            }
            is UiState.Success -> {
                FavouriteContent(
                    favouriteArtists = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavouriteContent(
    favouriteArtists: List<Artist>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier
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
            Text(
                text = stringResource(id = R.string.my_fav),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
        if(favouriteArtists.isNotEmpty()) {
            items(favouriteArtists) { artist ->
                ArtistItem(
                    image = artist.image,
                    name = artist.name,
                    modifier = modifier.clickable {
                        navigateToDetail(artist.name)
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
                    text = stringResource(id = R.string.error_zero_fav),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun FavouriteContentPreview() {
    FavouriteArtistTheme {
        FavouriteContent(
            favouriteArtists = FakeArtistDataSource.dummyArtist,
            modifier = Modifier,
            navigateToDetail = {}
        )
    }
}