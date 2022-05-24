package com.miguelete.post.data.server

import com.miguelete.data.source.RemoteDataSource
import com.miguelete.domain.Post
import com.miguelete.post.data.toDomain

class JsonPlaceholderDbDataSource: RemoteDataSource {
    override suspend fun getPostList(): List<Post> =
        JsonPlaceholderDb.service
            .getPostList()
            .map { it.toDomain() }

    override suspend fun getPost(id: Int): Post =
        JsonPlaceholderDb.service
            .getPost(id)
            .toDomain()
}
