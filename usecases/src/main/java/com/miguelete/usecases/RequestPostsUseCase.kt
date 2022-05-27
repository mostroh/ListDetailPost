package com.miguelete.usecases

import com.miguelete.data.repository.PostRepository
import com.miguelete.domain.Error
import javax.inject.Inject

class RequestPostsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(): Error? {
        return postRepository.requestPosts()
    }
}