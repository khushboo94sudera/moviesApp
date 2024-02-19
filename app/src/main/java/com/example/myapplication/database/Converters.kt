package com.example.myapplication.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list:List<Int>?):String?{
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(value:String?):List<Int>?{
        return value?.split(",")?.map { it.toInt() }
    }
}