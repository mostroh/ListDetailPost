package com.miguelete.data.repository

import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.domain.Error
import com.miguelete.domain.IPostRepository
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): IPostRepository {

    override val posts get() = localDataSource.posts

    override suspend fun requestPosts(): Error? {
        if (localDataSource.isEmpty()) {
            val posts = remoteDataSource.getPostList()
            posts.fold(
                ifLeft = { return it }
            ) {
                localDataSource.savePosts(it)
            }
        }
        return null
    }

    override fun getPost(id: Int) = localDataSource.findById(id)
}
