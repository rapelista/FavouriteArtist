package com.gvstang.dicoding.learning.favouriteartist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gvstang.dicoding.learning.favouriteartist.data.ArtistRepository
import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ArtistRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Artist>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Artist>>
        get() = _uiState

    fun getArtistByName(name: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getArtistByName(name))
        }
    }

    fun setFavourite(name: String, favorited: Boolean) {
        viewModelScope.launch {
//            repository.setFavourite(name, favorited)
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.setFavourite(name, favorited))
        }
    }

}