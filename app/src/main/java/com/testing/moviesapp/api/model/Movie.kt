package com.testing.moviesapp.api.model

data class Movie(
    val title: String?,
    val directorName: String?,
    val releaseYear: Int?,
    val actors: List<Actor>?
)

data class Actor(
    val actorName: String?
)