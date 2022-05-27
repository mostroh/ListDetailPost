package com.miguelete.post.ui.detail

import app.cash.turbine.test
import com.miguelete.post.ui.detail.DetailViewModel.UiState
import com.miguelete.post.testrules.CoroutinesTestRule
import com.miguelete.post.ui.toItemUiState
import com.miguelete.testshared.post2
import com.miguelete.usecases.GetPostUseCase
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
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPostUseCase: GetPostUseCase

    private lateinit var vm: DetailViewModel

    private val post = post2()

    @Before
    fun setup() {
        whenever(getPostUseCase(post.id)).thenReturn(flowOf(post))
        vm = DetailViewModel(post.id, getPostUseCase)
    }

    @Test
    fun `UI is updated with the post on start`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(post = post.toItemUiState()), awaitItem())
            cancel()
        }
    }

}