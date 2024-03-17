package com.suwaiphyo.todo.ui.screens.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suwaiphyo.todo.R
import com.suwaiphyo.todo.data.network.model.Todo
import com.suwaiphyo.todo.ui.screens.commonscreen.ErrorScreen
import com.suwaiphyo.todo.ui.screens.commonscreen.LoadingScreen
import com.suwaiphyo.todo.ui.theme.ToDoTheme
import com.suwaiphyo.todo.utilities.ext.toTaskStatus
import java.util.Locale

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val todoUiState by viewModel.todoUiState.collectAsState()
    when (todoUiState) {
        is TodoUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is TodoUiState.Success -> TodoListScreen(
            (todoUiState as TodoUiState.Success).todoResponse,
            modifier = Modifier.fillMaxSize()
        )

        is TodoUiState.Error -> ErrorScreen({}, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun TodoListScreen(
    listTodo: List<Todo>,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(listTodo) { todo ->
                TodoCard(
                    todo = todo,
                    modifier = modifier
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun TodoCard(todo: Todo, modifier: Modifier = Modifier) {
    val textColor =
        if (todo.completed == true) colorResource(id = R.color.green) else colorResource(id = R.color.red)
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column {
            Text(
                text = todo.title.orEmpty().uppercase(Locale.ROOT),
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            Text(
                text = todo.completed?.toTaskStatus().toString(),
                style = MaterialTheme.typography.labelMedium,
                color = textColor,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    ToDoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val todos = mutableListOf(
                Todo(title = "title", completed = true),
                Todo(title = "title", completed = false),
                Todo(title = "title", completed = true),
                Todo(title = "title", completed = false)
            )
            TodoListScreen(todos, modifier = Modifier)
        }
    }
}