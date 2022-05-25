package com.miguelete.post.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelete.post.ui.toItemUiState
import com.miguelete.usecases.GetPostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val getPostListUseCase: GetPostListUseCase): ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state : StateFlow<MainUiState> = _state.asStateFlow()

    init {
        refresh()
    }

    data class MainUiState(
        val loading : Boolean = false,
        val posts: List<PostItemUiState>? = null,
        val navigateTo: String? = null
    )

    private fun refresh() {
        viewModelScope.launch {
            _state.value = MainUiState(loading = true)
            _state.value = MainUiState(posts =
                getPostListUseCase.invoke()
                    .map { it.toItemUiState() }
            )
        }
    }

    fun onPostClick(post: PostItemUiState) {
        //TODO
    }
}
