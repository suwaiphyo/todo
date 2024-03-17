package com.suwaiphyo.todo.data.network.repositories

import com.suwaiphyo.todo.data.local.TodoDatabase
import com.suwaiphyo.todo.data.network.api.services.TodoApiService
import com.suwaiphyo.todo.data.network.model.Todo
import com.suwaiphyo.todo.ui.screens.todolist.todoToTodoEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TodoRepository {
    suspend fun insertTodo(todo : List<Todo>)
    suspend fun fetchTodo() : List<Todo>
}

@Singleton
class TodoNetworkRepositoryImpl @Inject constructor(
    private val todoDb: TodoDatabase,
    private val todoApiService: TodoApiService,
) : TodoRepository {
    override suspend fun insertTodo(todos : List<Todo>) {
        withContext(Dispatchers.IO) {
            todoDb.todoDao().insertTodo(
                todoToTodoEntity(todos)
            )
        }
    }

    override suspend fun fetchTodo(): List<Todo> {
        val result : List<Todo> = try {
            todoApiService.getTodo()
        } catch (e: Exception) {
            emptyList()
        }
        return result
    }
}