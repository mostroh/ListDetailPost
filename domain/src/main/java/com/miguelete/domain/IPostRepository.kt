package com.miguelete.domain

import kotlinx.coroutines.flow.Flow


interface IPostRepository
{
    val posts: Flow<List<Post>>
    suspend fun requestPosts(): Error?
    fun getPost(id: Int): Flow<Post>
}