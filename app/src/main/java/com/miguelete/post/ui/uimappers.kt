package com.miguelete.post.ui

import com.miguelete.domain.Post
import com.miguelete.post.ui.main.PostItemUiState

fun Post.toItemUiState() = PostItemUiState(
    id,
    userId,
    title,
    body
)
