package com.miguelete.data.source

import com.miguelete.domain.Post

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun savePosts(posts: List<Post>)
    suspend fun getPostList(): List<Post>
    suspend fun findById(id: Int): Post
    suspend fun update(post: Post)
}
