package com.suwaiphyo.todo.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName(value = "userId")
    val userId: Int? = 0,
    @SerialName(value = "id")
    val id: Int? = 0,
    @SerialName(value = "title")
    val title: String? = "",
    @SerialName(value = "completed")
    val completed: Boolean? = false
)