package com.miguelete.usecases

import com.miguelete.domain.IPostRepository
import com.miguelete.domain.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val repository: IPostRepository) {

    operator fun invoke(id: Int): Flow<Post> = repository.getPost(id)
}
