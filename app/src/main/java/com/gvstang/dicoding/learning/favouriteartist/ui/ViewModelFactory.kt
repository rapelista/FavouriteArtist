package com.gvstang.dicoding.learning.favouriteartist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gvstang.dicoding.learning.favouriteartist.data.ArtistRepository
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ArtistRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}