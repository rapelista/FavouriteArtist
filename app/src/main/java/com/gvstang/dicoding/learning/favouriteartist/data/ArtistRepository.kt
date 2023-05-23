package com.gvstang.dicoding.learning.favouriteartist.data

import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.model.FakeArtistDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArtistRepository {

    private val favouriteArtists = mutableListOf<Artist>()
    private val listArtists = mutableListOf<Artist>()

    init {
        if(listArtists.isEmpty()) {
            FakeArtistDataSource.dummyArtist.forEach {
                listArtists.add(it)
            }
        }
    }

    fun getAllArtists(): Flow<List<Artist>> = flowOf(listArtists)

    fun searchArtist(query: String): List<Artist> =
        listArtists.filter {
            it.name.contains(query, ignoreCase = true)
        }

    companion object {
        @Volatile
        private var instance: ArtistRepository? = null

        fun getInstance(): ArtistRepository =
            instance ?: synchronized(this) {
                ArtistRepository().apply {
                    instance = this
                }
            }
    }
}