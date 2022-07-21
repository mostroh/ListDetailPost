package com.miguelete.post.ui

import com.miguelete.data.repository.PostRepository
import com.miguelete.post.FakePostDao
import com.miguelete.post.FakeRemoteService
import com.miguelete.data.database.PostEntity
import com.miguelete.data.database.RoomDataSource
import com.miguelete.data.server.JsonPlaceholderDbDataSource
import com.miguelete.data.server.apimodels.PostApiModel

fun buildRepositoryWith(
    localData: List<PostEntity>,
    remoteData: List<PostApiModel>
): PostRepository {
    val localDataSource = RoomDataSource(FakePostDao(localData))
    val remoteDataSource = JsonPlaceholderDbDataSource(FakeRemoteService(remoteData))
    return PostRepository(localDataSource, remoteDataSource)
}

fun buildDatabasePosts(vararg id: Int) = id.map {
    PostEntity(
        id = it,
        userId = it,
        title = "Title $it",
        body = "Body $it"
    )
}

fun buildRemotePosts(vararg id: Int) = id.map {
    PostApiModel(
        id = it,
        userId = it,
        title = "Title $it",
        body = "Body $it"
    )
}