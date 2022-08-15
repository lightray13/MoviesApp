package com.testing.moviesapp.data.local.repository

import android.util.Log
import com.testing.moviesapp.api.Result
import com.testing.moviesapp.api.successed
import com.testing.moviesapp.data.local.database.MoviesListEntity
import com.testing.moviesapp.data.local.preferences.PreferenceStorage
import com.testing.moviesapp.util.Constants
import java.util.*
import javax.inject.Inject

class MoviesListRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val preferenceStorage: PreferenceStorage
) {
    val allMoviesList = localDataSource.allMoviesList

    suspend fun moviesList() {
        val result = remoteDataSource.moviesList()
        Log.d("myLogs", result.toString())
        when(result) {
            is Result.Success -> {
                if (result.successed) {
                    val moviesList = result.data.let {
                        it.list.filter { item -> item.title.isNullOrEmpty().not() }
                            .map { movie ->
                                MoviesListEntity(
                                    movie.title ?: "",
                                    movie.directorName,
                                    movie.releaseYear,
                                    movie.actors.toString()
                                )
                            }
                    }
                    localDataSource.insertMoviesIntoDatabase(moviesList)
                    preferenceStorage.timeLoadedAt = Date().time
                    Result.Success(true)
                } else {
                    Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }

    fun loadData(): Boolean {
        val lastLoadedTime = preferenceStorage.timeLoadedAt
        val currentTime = Date().time
        return currentTime - lastLoadedTime > 30 * 1000
    }
}