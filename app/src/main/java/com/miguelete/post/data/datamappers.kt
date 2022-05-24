package com.miguelete.post.data

import com.miguelete.domain.Post
import com.miguelete.post.data.server.apimodels.PostApiModel

fun PostApiModel.toDomain() = Post(
    id,
    userId,
    title,
    body
)