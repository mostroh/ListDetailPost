package com.miguelete.data.repository

import com.miguelete.data.source.RemoteDataSource
import com.miguelete.domain.Post

class PostRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getPostList(): List<Post>  = remoteDataSource.getPostList()

    suspend fun getPost(id: Int): Post  = remoteDataSource.getPost(id)
}
