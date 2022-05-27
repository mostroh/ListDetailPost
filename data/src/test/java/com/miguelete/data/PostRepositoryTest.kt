package com.miguelete.data

import arrow.core.right
import com.miguelete.data.repository.PostRepository
import com.miguelete.data.source.LocalDataSource
import com.miguelete.data.source.RemoteDataSource
import com.miguelete.testshared.post1
import com.miguelete.testshared.posts
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryTest {


    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var postRepository: PostRepository

    private val localPosts = flowOf(posts())

    @Before
    fun setup() {
        whenever(localDataSource.posts).thenReturn(localPosts)
        postRepository = PostRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `Posts are taken from local data source if available`(): Unit = runBlocking {

        val result = postRepository.posts

        assertEquals(localPosts, result)
    }

    @Test
    fun `Posts are saved to local data source when it's empty`(): Unit = runBlocking {
        val remotePosts = posts()
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.getPostList()).thenReturn(remotePosts.right())

        postRepository.requestPosts()

        verify(localDataSource).savePosts(remotePosts)
    }

    @Test
    fun `Finding a post by id is done in local data source`(): Unit = runBlocking {
        val postId = post1().id
        val postFlow = flowOf(post1())
        whenever(localDataSource.findById(postId)).thenReturn(postFlow)

        val result = postRepository.getPost(postId)

        assertEquals(postFlow, result)
    }
}
