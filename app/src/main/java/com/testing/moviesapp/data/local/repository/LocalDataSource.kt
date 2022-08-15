package com.testing.moviesapp.data.local.repository

import androidx.lifecycle.LiveData
import com.testing.moviesapp.data.local.database.MovieDatabase
import com.testing.moviesapp.data.local.database.MoviesListEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: MovieDatabase) {
    val allMoviesList: LiveData<List<MoviesListEntity>> = database.moviesListDao().moviesList()

    suspend fun insertMoviesIntoDatabase(moviesList: List<MoviesListEntity>) {
        if (moviesList.isNotEmpty()) {
            database.moviesListDao().insert(moviesList)
        }
    }
}