package com.suwaiphyo.todo.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do")
data class TodoEntity(
    @PrimaryKey
    val id: Int? = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int? = 0,
    val title: String? = "",
    val completed: Boolean? = false
)