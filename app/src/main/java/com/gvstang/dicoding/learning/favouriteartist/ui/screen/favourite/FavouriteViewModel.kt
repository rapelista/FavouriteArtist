package com.gvstang.dicoding.learning.favouriteartist.ui.screen.favourite

import androidx.lifecycle.ViewModel
import com.gvstang.dicoding.learning.favouriteartist.data.ArtistRepository
import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouriteViewModel(private val repository: ArtistRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Artist>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Artist>>>
        get() = _uiState

    fun getFavouritedArtists() {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getFavouritedArtists())
    }

}