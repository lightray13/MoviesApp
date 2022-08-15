package com.testing.moviesapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MoviesListEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converter::class])
abstract class MovieDatabase: RoomDatabase() {
    abstract fun moviesListDao(): MoviesListDao

    companion object {
        fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "Movies").build()
        }
    }
}