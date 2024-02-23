package com.example.myapplication.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list:List<String>?):String?{
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(value:String?):List<String>?{
        return value?.split(",")?.map { it }
    }
}