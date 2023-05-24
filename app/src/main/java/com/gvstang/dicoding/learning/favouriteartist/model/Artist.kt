package com.gvstang.dicoding.learning.favouriteartist.model

import kotlin.random.Random

data class Artist(
    val name: String,
    val image: Int,
    val genres: List<String>,
    val description: String,
    val favorited: Boolean = Random.nextBoolean()
)
