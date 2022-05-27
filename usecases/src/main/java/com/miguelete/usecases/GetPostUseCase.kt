package com.miguelete.usecases

import com.miguelete.data.repository.PostRepository
import com.miguelete.domain.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(id: Int): Flow<Post> = repository.getPost(id)
}
