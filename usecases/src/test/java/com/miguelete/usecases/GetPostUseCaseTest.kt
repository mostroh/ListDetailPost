package com.miguelete.usecases

import com.miguelete.testshared.post1
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPostUseCaseTest {
    @Test
    fun `Invoke calls post repository`(): Unit = runBlocking {
        val postFlow = flowOf(post1())
        val getPostUseCase = GetPostUseCase(mock {
            on { getPost(post1().id) } doReturn (postFlow)
        })

        val result = getPostUseCase(1)

        assertEquals(postFlow, result)
    }
}
