package com.testing.moviesapp.api

import com.testing.moviesapp.api.model.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/constanta-android-dev/intership-wellcome-task/main/films.json")
    suspend fun moviesList(): Response<MovieList>
}