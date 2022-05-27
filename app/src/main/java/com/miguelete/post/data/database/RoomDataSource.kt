package com.miguelete.post.data.database

import com.miguelete.data.source.LocalDataSource
import com.miguelete.domain.Post
import com.miguelete.post.data.toDomain
import com.miguelete.post.data.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val postDao: PostDao): LocalDataSource {

    override suspend fun isEmpty() =
        withContext(Dispatchers.IO) { postDao.postCount() <=0 }

    override suspend fun savePosts(posts: List<Post>) {
        withContext(Dispatchers.IO) {
            postDao.insertPosts(posts.map { it.toEntity() })
        }
    }

    override suspend fun getPostList() =
        withContext(Dispatchers.IO) {
            postDao.getAll().map { it.toDomain() }
        }

    override suspend fun findById(id: Int) =
        withContext(Dispatchers.IO) {
            postDao.findById(id).toDomain()
        }

    override suspend fun update(post: Post) {
        withContext(Dispatchers.IO) {
            postDao.updatePost(post.toEntity())
        }
    }
}
