package com.miguelete.usecases

import com.miguelete.data.repository.PostRepository
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(private val postRepository: PostRepository) {
    operator fun invoke() = postRepository.posts
}
