package com.gvstang.dicoding.learning.favouriteartist.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gvstang.dicoding.learning.favouriteartist.data.ArtistRepository
import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ArtistRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Artist>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Artist>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllArtists() {
        viewModelScope.launch {
            repository.getAllArtists()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listArtists ->
                    _uiState.value = if(listArtists.isEmpty()) {
                        UiState.Error("")
                    } else {
                        UiState.Success(listArtists)
                    }
                }
        }
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UiState.Success(repository.searchArtist(newQuery))
    }

}