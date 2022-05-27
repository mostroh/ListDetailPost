package com.miguelete.post.ui.detail

import app.cash.turbine.test
import com.miguelete.post.data.database.PostEntity
import com.miguelete.post.data.server.apimodels.PostApiModel
import com.miguelete.post.ui.detail.DetailViewModel.UiState
import com.miguelete.post.testrules.CoroutinesTestRule
import com.miguelete.post.ui.buildDatabasePosts
import com.miguelete.post.ui.buildRepositoryWith
import com.miguelete.usecases.GetPostUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `UI is updated with the post on start`() = runTest {
        val vm = buildViewModelWith(
            id = 2,
            localData = buildDatabasePosts(1, 2, 3)
        )

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(2, awaitItem().post!!.id)
            cancel()
        }
    }

    private fun buildViewModelWith(
        id: Int,
        localData: List<PostEntity> = emptyList(),
        remoteData: List<PostApiModel> = emptyList()
    ): DetailViewModel {
        val postRepository = buildRepositoryWith(localData, remoteData)
        val getPostUseCase = GetPostUseCase(postRepository)
        return DetailViewModel(id, getPostUseCase)
    }
}
