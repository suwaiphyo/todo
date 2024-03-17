package com.suwaiphyo.todo.data.local.repositories

import com.suwaiphyo.todo.data.local.dao.TodoDao
import com.suwaiphyo.todo.data.local.entities.TodoEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

interface TodoOfflineRepository {
    /**
     * Retrieve all the to do list from the the given data source.
     */
    fun getOfflineTodo(): Flow<List<TodoEntity>>

    /**
     * Insert to do list
     */
    suspend fun insertTodo(todoList: List<TodoEntity>)

    /**
     * remove all offline to do list from room
     */
    suspend fun deleteTodo(todoList: List<TodoEntity>)
}

@Singleton
class TodoOfflineRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoOfflineRepository {
    override fun getOfflineTodo(): Flow<List<TodoEntity>> = todoDao.getTodo()

    override suspend fun insertTodo(todoList: List<TodoEntity>) = todoDao.insertTodo(todoList)

    override suspend fun deleteTodo(todoList: List<TodoEntity>) = todoDao.deleteTodo(todoList)
}