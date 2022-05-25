package com.miguelete.post.di

import com.miguelete.data.repository.PostRepository
import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun postRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = PostRepository(localDataSource, remoteDataSource)
}
