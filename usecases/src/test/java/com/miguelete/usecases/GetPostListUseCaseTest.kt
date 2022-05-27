package com.miguelete.usecases

import com.miguelete.testshared.posts
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPostListUseCaseTest {
    @Test
    fun `Invoke calls posts repository`(): Unit = runBlocking {
        val postFlow = flowOf(posts())
        val getPostListUseCase = GetPostListUseCase(mock {
            on { posts } doReturn postFlow
        })

        val result = getPostListUseCase()

        Assert.assertEquals(postFlow, result)
    }
}
