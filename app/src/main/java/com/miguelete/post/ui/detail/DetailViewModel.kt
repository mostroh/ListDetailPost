package com.miguelete.post.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelete.domain.Error
import com.miguelete.post.di.PostId
import com.miguelete.post.toError
import com.miguelete.post.ui.main.PostItemUiState
import com.miguelete.post.ui.toItemUiState
import com.miguelete.usecases.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @PostId private val postId: Int,
    getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPostUseCase(postId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { post -> _state.update { UiState(post = post.toItemUiState()) } }
        }
    }

    data class UiState(val post: PostItemUiState? = null, val error: Error? = null)
}
