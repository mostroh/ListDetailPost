package com.miguelete.data.database

import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.tryCall
import com.miguelete.domain.Post
import com.miguelete.domain.Error
import com.miguelete.data.toDomain
import com.miguelete.data.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class RoomDataSource @Inject constructor(private val postDao: PostDao): LocalDataSource {

    override val posts: Flow<List<Post>> = postDao.getAll().map { it.toDomain() }

    override suspend fun isEmpty() = postDao.postCount() == 0

    override suspend fun savePosts(posts: List<Post>): Error? = tryCall {
        postDao.insertPosts(posts.toEntity())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override fun findById(id: Int) = postDao.findById(id).map { it.toDomain() }
}
