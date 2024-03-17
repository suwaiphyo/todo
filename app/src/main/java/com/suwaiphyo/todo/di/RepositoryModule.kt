package com.suwaiphyo.todo.di

import com.suwaiphyo.todo.data.local.TodoDatabase
import com.suwaiphyo.todo.data.local.dao.TodoDao
import com.suwaiphyo.todo.data.local.repositories.TodoOfflineRepository
import com.suwaiphyo.todo.data.local.repositories.TodoOfflineRepositoryImpl
import com.suwaiphyo.todo.data.network.api.services.TodoApiService
import com.suwaiphyo.todo.data.network.repositories.TodoNetworkRepositoryImpl
import com.suwaiphyo.todo.data.network.repositories.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun bindTodoRepository(todoDb: TodoDatabase, apiService: TodoApiService): TodoRepository {
        return TodoNetworkRepositoryImpl(todoDb, apiService)
    }

    @Provides
    @Singleton
    fun bindTodoOfflineRepository(todoDao: TodoDao): TodoOfflineRepository {
        return TodoOfflineRepositoryImpl(todoDao)
    }
}