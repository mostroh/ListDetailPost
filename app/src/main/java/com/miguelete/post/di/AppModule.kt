package com.miguelete.post.di

import android.app.Application
import androidx.room.Room
import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.post.data.database.PostDatabase
import com.miguelete.post.data.database.RoomDataSource
import com.miguelete.post.data.server.JsonPlaceholderDbDataSource
import com.miguelete.post.data.server.PostService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PostDatabase::class.java,
        "post-db"
    ).build()

    @Provides
    @Singleton
    fun providePostDao(db: PostDatabase) = db.postDao()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): PostService {

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: RoomDataSource): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: JsonPlaceholderDbDataSource): RemoteDataSource
}
