package com.miguelete.post.di

import android.app.Application
import androidx.room.Room
import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.post.data.database.PostDatabase
import com.miguelete.post.data.database.RoomDataSource
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
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PostDatabase::class.java,
        "post-db"
    ).build()

    @Provides
    @Singleton
    fun postDaoProvider(db: PostDatabase) = db.postDao()

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = JsonPlaceholderDbDataSource()

    @Provides
    fun localDataSourceProvider(db: PostDatabase): LocalDataSource = RoomDataSource(db)
}
