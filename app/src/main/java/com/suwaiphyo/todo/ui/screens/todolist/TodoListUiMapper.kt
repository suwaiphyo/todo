package com.suwaiphyo.todo.ui.screens.todolist

import com.suwaiphyo.todo.data.local.entities.TodoEntity
import com.suwaiphyo.todo.data.network.model.Todo

fun todoToTodoEntity(todoList: List<Todo>) : List<TodoEntity> {
    return todoList.map { todo ->
        TodoEntity(
            userId = todo.userId,
            id = todo.id,
            title = todo.title,
            completed = todo.completed
        )
    }
}

fun TodoEntity.toTodo(): Todo = Todo(
    userId = userId,
    id = id,
    title = title,
    completed = completed
)
