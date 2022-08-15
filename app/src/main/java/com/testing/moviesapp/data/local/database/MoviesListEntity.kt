package com.testing.moviesapp.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_list")
data class MoviesListEntity(
    @PrimaryKey val title: String,
    val directorName: String?,
    val releaseYear: Int?,
    val actors: String
)