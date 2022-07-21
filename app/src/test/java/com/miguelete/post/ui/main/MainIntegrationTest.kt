package com.miguelete.post.ui.main

import app.cash.turbine.test
import com.miguelete.data.database.PostEntity
import com.miguelete.data.server.apimodels.PostApiModel
import com.miguelete.post.ui.main.MainViewModel.MainUiState
import com.miguelete.post.testrules.CoroutinesTestRule
import com.miguelete.post.ui.buildDatabasePosts
import com.miguelete.post.ui.buildRemotePosts
import com.miguelete.post.ui.buildRepositoryWith
import com.miguelete.usecases.GetPostListUseCase
import com.miguelete.usecases.RequestPostsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemotePosts(4, 5, 6)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.onUiReady()

        vm.state.test {
            assertEquals(MainUiState(), awaitItem())
            assertEquals(MainUiState(posts = emptyList()), awaitItem())
            assertEquals(MainUiState(posts = emptyList(), loading = true), awaitItem())
            assertEquals(MainUiState(posts = emptyList(), loading = false), awaitItem())

            val posts = awaitItem().posts!!
            assertEquals("Title 4", posts[0].title)
            assertEquals("Title 5", posts[1].title)
            assertEquals("Title 6", posts[2].title)

            cancel()
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = buildDatabasePosts(1, 2, 3)
        val remoteData = buildRemotePosts(4, 5, 6)
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData
        )

        vm.state.test {
            assertEquals(MainUiState(), awaitItem())

            val posts = awaitItem().posts!!
            assertEquals("Title 1", posts[0].title)
            assertEquals("Title 2", posts[1].title)
            assertEquals("Title 3", posts[2].title)

            cancel()
        }
    }

    private fun buildViewModelWith(
        localData: List<PostEntity> = emptyList(),
        remoteData: List<PostApiModel> = emptyList()
    ): MainViewModel {
        val postRepository = buildRepositoryWith(localData, remoteData)
        val getPostListUseCase = GetPostListUseCase(postRepository)
        val requestPostsUseCase = RequestPostsUseCase(postRepository)
        return MainViewModel(getPostListUseCase, requestPostsUseCase)
    }
}