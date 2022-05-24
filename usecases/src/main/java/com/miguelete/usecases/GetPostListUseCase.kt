package com.miguelete.usecases

import com.miguelete.data.repository.PostRepository
import com.miguelete.domain.Post

class GetPostListUseCase(private val postRepository: PostRepository) {
    suspend fun invoke(): List<Post> = postRepository.getPostList()
}
