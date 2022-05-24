package com.miguelete.post.di

import android.app.Application
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.post.data.server.JsonPlaceholderDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = JsonPlaceholderDbDataSource()
}
