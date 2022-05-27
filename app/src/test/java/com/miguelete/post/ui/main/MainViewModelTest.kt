package com.miguelete.post.ui.main

import app.cash.turbine.test
import com.miguelete.post.ui.main.MainViewModel.MainUiState
import com.miguelete.post.testrules.CoroutinesTestRule
import com.miguelete.post.ui.toItemUiState
import com.miguelete.testshared.posts
import com.miguelete.usecases.GetPostListUseCase
import com.miguelete.usecases.RequestPostsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPostListUseCase: GetPostListUseCase

    @Mock
    lateinit var requestPostsUseCase: RequestPostsUseCase

    private lateinit var vm: MainViewModel

    private val posts = posts()

    @Before
    fun setup() {
        whenever(getPostListUseCase()).thenReturn(flowOf(posts))
        vm = MainViewModel(getPostListUseCase, requestPostsUseCase)
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(MainUiState(), awaitItem())
            assertEquals(MainUiState(posts = posts.map { it.toItemUiState() }), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when screen starts and hidden when it finishes requesting post`() =
        runTest {
            vm.onUiReady()

            vm.state.test {
                assertEquals(MainUiState(), awaitItem())
                assertEquals(MainUiState(posts = posts.map { it.toItemUiState() }), awaitItem())
                assertEquals(MainUiState(posts = posts.map { it.toItemUiState() }, loading = true), awaitItem())
                assertEquals(MainUiState(posts = posts.map { it.toItemUiState() }, loading = false), awaitItem())
                cancel()
            }
        }

    @Test
    fun `Posts are requested when UI screen starts`() = runTest {
        vm.onUiReady()
        runCurrent()

        verify(requestPostsUseCase).invoke()
    }
}