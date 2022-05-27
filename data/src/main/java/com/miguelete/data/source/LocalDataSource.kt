package com.miguelete.data.source

import com.miguelete.domain.Post
import com.miguelete.domain.Error
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val posts: Flow<List<Post>>

    suspend fun isEmpty(): Boolean
    suspend fun savePosts(posts: List<Post>): Error?
    suspend fun findById(id: Int): Flow<Post>
}
