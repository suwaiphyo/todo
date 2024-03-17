package com.suwaiphyo.todo.utilities.ext

fun Boolean.toTaskStatus(): String {
    return if (this) {
        "Completed"
    } else {
        "To do"
    }
}