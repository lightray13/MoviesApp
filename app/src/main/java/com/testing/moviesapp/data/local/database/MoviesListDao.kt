package com.testing.moviesapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesListDao {

    @Query("SELECT * FROM movies_list ORDER BY releaseYear ASC")
    fun moviesList(): LiveData<List<MoviesListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MoviesListEntity>)

    @Query("DELETE FROM movies_list")
    suspend fun deleteAll()
}