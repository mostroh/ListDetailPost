package com.miguelete.data.source

import com.miguelete.domain.Post

interface RemoteDataSource {
    suspend fun getPostList(): List<Post>
    suspend fun getPost(id: Int): Post
}
