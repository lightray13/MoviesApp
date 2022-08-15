package com.testing.moviesapp.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.testing.moviesapp.api.model.Actor
import java.lang.reflect.Type

class Converter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun listToString(list: List<Actor>): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun stringToList(data: String): List<Actor> {
            val listType: Type = object: TypeToken<List<Actor?>?>(){}.type
            return Gson().fromJson(data, listType)
        }
    }
}