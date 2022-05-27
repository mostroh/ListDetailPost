package com.miguelete.usecases

import com.miguelete.data.repository.PostRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RequestPostUseCaseTest {

    @Test
    fun `Invoke calls post repository`(): Unit = runBlocking {
        val postRepository = mock<PostRepository>()
        val requestPostsUseCase = RequestPostsUseCase(postRepository)

        requestPostsUseCase()

        verify(postRepository).requestPosts()
    }
}
