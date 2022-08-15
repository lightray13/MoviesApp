package com.testing.moviesapp.api.model

import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("items")
    val list: List<Movie>
)