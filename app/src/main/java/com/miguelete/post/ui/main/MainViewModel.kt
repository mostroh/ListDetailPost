package com.miguelete.post.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelete.domain.Post
import com.miguelete.usecases.GetPostListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private  val getPostListUseCase: GetPostListUseCase): ViewModel() {
    private val _model = MutableLiveData<MainUiState>()
    val model : LiveData<MainUiState>
    get() {
        if (_model.value == null) refresh()
        return _model
    }

    sealed class MainUiState {
        object Loading : MainUiState()
        class Content(val post: List<Post>) : MainUiState()
        class Navigation(val postId: Int) : MainUiState()

        fun isLoading() = this is Loading
    }

    fun refresh() {
        viewModelScope.launch {
            _model.value = MainUiState.Loading
            _model.value = MainUiState.Content(getPostListUseCase.invoke())
        }
    }

    fun onPostClick(post: Post) {
        //TODO
    }
}
