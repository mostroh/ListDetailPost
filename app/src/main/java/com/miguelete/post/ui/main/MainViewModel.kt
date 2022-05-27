package com.miguelete.post.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelete.domain.Error
import com.miguelete.post.toError
import com.miguelete.post.ui.toItemUiState
import com.miguelete.usecases.GetPostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        val error: Error? = null
    )

    private fun refresh() {
        viewModelScope.launch {
            getPostListUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { posts ->
                    _state.update { MainUiState(posts = posts.map { it.toItemUiState() }) }
                }
        }
    }

    fun onPostClick(post: PostItemUiState) {
        //TODO
    }
}
