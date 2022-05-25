package com.miguelete.data.repository

import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.domain.Post

class PostRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getPostList(): List<Post> {
        if (localDataSource.isEmpty()) {
            val posts = remoteDataSource.getPostList()
            localDataSource.savePosts(posts)
        }

        return localDataSource.getPostList()
    }

    suspend fun getPost(id: Int): Post {
        try {
            val post = remoteDataSource.getPost(id)
            localDataSource.update(post)
        } catch (e: Exception) {

        }
        return localDataSource.findById(id)
    }
}
