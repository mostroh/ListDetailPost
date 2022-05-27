package com.miguelete.post

import com.miguelete.post.data.database.PostDao
import com.miguelete.post.data.database.PostEntity
import com.miguelete.post.data.server.PostService
import com.miguelete.post.data.server.apimodels.PostApiModel
import com.miguelete.post.data.toDomain
import com.miguelete.post.data.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePostDao(posts: List<PostEntity> = emptyList()) : PostDao {

    private val inMemoryPost = MutableStateFlow(posts)
    private lateinit var findPostFlow: MutableStateFlow<PostEntity>

    override fun getAll(): Flow<List<PostEntity>> = inMemoryPost

    override fun findById(id: Int): Flow<PostEntity> {
        findPostFlow = MutableStateFlow(inMemoryPost.value.first { it.id == id })
        return findPostFlow
    }

    override suspend fun postCount(): Int = inMemoryPost.value.size

    override suspend fun insertPosts(posts: List<PostEntity>) {
        inMemoryPost.value = posts

        if (::findPostFlow.isInitialized) {
            posts.firstOrNull { it.id == findPostFlow.value.id }
                ?.let { findPostFlow.value = it }
        }

    }

}

class FakeRemoteService(private val posts: List<PostApiModel> = emptyList()) : PostService {

    override suspend fun getPostList() = posts

}
