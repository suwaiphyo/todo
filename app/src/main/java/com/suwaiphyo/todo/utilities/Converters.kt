package com.suwaiphyo.todo.utilities

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return listOf(*value.split(",").map { it.toInt() }.toTypedArray()).orEmpty()
    }

    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(",")
    }
}
