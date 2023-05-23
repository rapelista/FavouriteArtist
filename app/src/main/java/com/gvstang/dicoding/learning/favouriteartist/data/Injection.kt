package com.gvstang.dicoding.learning.favouriteartist.data

object Injection {

    fun provideRepository(): ArtistRepository = ArtistRepository.getInstance()

}