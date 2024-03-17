package com.suwaiphyo.todo.data.network.api.services

import com.suwaiphyo.todo.data.network.model.Todo
import retrofit2.http.GET

/**
 * A public interface that exposes the [getTodo] method
 */
interface TodoApiService {
    /**
     * Returns to do list and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "todos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("todos")
    suspend fun getTodo() : List<Todo>
}