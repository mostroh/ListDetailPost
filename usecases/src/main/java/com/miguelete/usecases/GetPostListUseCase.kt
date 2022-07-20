package com.miguelete.usecases

import com.miguelete.domain.IPostRepository
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(private val postRepository: IPostRepository) {
    operator fun invoke() = postRepository.posts
}
