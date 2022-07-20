package com.miguelete.usecases

import com.miguelete.domain.Error
import com.miguelete.domain.IPostRepository
import javax.inject.Inject

class RequestPostsUseCase @Inject constructor(private val postRepository: IPostRepository) {
    suspend operator fun invoke(): Error? {
        return postRepository.requestPosts()
    }
}