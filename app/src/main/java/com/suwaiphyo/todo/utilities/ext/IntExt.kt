package com.suwaiphyo.todo.utilities.ext

/**
 * Returns the Int if it is not null, or zero otherwise.
 */
inline fun Int?.orZero(): Int = this ?: 0