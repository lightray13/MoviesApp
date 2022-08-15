package com.testing.moviesapp.data.local.repository

import com.testing.moviesapp.api.Result
import com.testing.moviesapp.api.ApiInterface
import com.testing.moviesapp.api.BaseRemoteDataSource
import com.testing.moviesapp.api.model.MovieList
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {

    suspend fun moviesList(): Result<MovieList> =
        getResult {
            service.moviesList()
        }
}