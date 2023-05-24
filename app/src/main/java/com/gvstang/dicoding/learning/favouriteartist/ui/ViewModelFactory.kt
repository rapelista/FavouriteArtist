package com.gvstang.dicoding.learning.favouriteartist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gvstang.dicoding.learning.favouriteartist.data.ArtistRepository
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.detail.DetailViewModel
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.favourite.FavouriteViewModel
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ArtistRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return FavouriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}