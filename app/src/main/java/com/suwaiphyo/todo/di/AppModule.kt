package com.suwaiphyo.todo.di

import android.content.Context
import androidx.room.Room
import com.suwaiphyo.todo.utilities.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.suwaiphyo.todo.data.local.TodoDatabase
import com.suwaiphyo.todo.utilities.TODO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        ).baseUrl(BASE_URL).client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        HttpLoggingInterceptor.Level.BODY // Set the log level (BASIC, HEADERS, BODY)
                }).build()
        ).build()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, TODO_DATABASE).build()
    }
}