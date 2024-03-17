package com.suwaiphyo.todo.ui.screens.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suwaiphyo.todo.data.local.repositories.TodoOfflineRepository
import com.suwaiphyo.todo.data.network.model.Todo
import com.suwaiphyo.todo.data.network.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI state for the To do List Screen
 */
sealed interface TodoUiState {
    data class Success(val todoResponse: List<Todo>) : TodoUiState
    object Error : TodoUiState
    object Loading : TodoUiState
}

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val todoOfflineRepository: TodoOfflineRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    private val _todoUiStateFlow: MutableStateFlow<TodoUiState> = MutableStateFlow(TodoUiState.Loading)
    val todoUiState: StateFlow<TodoUiState> = _todoUiStateFlow.asStateFlow()

    init {
        getTodoListFromDB()
        getTodoList()
    }

    /**
     * Get to do information from the To do API Retrofit service and updates the
     * [Todo] [List] [MutableList]
     */
    private fun getTodoList() {
        viewModelScope.launch {
            val todoFromRemote = todoRepository.fetchTodo()
            todoRepository.insertTodo(todoFromRemote)
        }
    }

    private fun getTodoListFromDB() {
        viewModelScope.launch {
            todoOfflineRepository.getOfflineTodo()
                .catch {
                    TodoUiState.Error
                }.collectLatest { items ->
                    _todoUiStateFlow.update { TodoUiState.Success(items.map { it.toTodo() }) }
                }
        }
    }
}



