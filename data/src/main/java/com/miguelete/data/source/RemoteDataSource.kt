package com.miguelete.data.source

import arrow.core.Either
import com.miguelete.domain.Error
import com.miguelete.domain.Post

interface RemoteDataSource {
    suspend fun getPostList(): Either<Error, List<Post>>
}
