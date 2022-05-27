package com.miguelete.post.data.server

import arrow.core.Either
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.domain.Post
import com.miguelete.domain.Error
import com.miguelete.post.data.toDomainModel
import com.miguelete.post.tryCall
import javax.inject.Inject

class JsonPlaceholderDbDataSource @Inject constructor(
    private val postRemoteService: PostService
): RemoteDataSource {
    override suspend fun getPostList(): Either<Error, List<Post>> = tryCall {
        postRemoteService
            .getPostList()
            .toDomainModel()
    }
}
