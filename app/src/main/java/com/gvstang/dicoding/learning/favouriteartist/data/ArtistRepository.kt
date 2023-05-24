package com.gvstang.dicoding.learning.favouriteartist.data

import com.gvstang.dicoding.learning.favouriteartist.model.Artist
import com.gvstang.dicoding.learning.favouriteartist.model.FakeArtistDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArtistRepository {

    private val listArtists = mutableListOf<Artist>()

    init {
        if(listArtists.isEmpty()) {
            FakeArtistDataSource.dummyArtist.forEach {
                listArtists.add(it)
            }
        }
        listArtists.shuffle()
    }

    fun getAllArtists(): Flow<List<Artist>> = flowOf(listArtists)

    fun searchArtist(query: String): List<Artist> =
        listArtists.filter {
            it.name.contains(query, ignoreCase = true)
        }

    fun getArtistByName(name: String): Artist =
        listArtists.first {
            it.name == name
        }

    fun setFavourite(name: String, favorited: Boolean): Artist {
        val index = listArtists.indexOfFirst { it.name == name }
        if(index >= 0) {
            val newArtist = getArtistByName(name).copy(favorited = favorited)
            listArtists[index] = newArtist
            return newArtist
        }
        return getArtistByName(name)
    }

    fun getFavouritedArtists(): List<Artist> = listArtists.filter { it.favorited }

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