package com.suwaiphyo.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suwaiphyo.todo.data.local.dao.TodoDao
import com.suwaiphyo.todo.data.local.entities.TodoEntity
import com.suwaiphyo.todo.utilities.Converters

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}