package com.miguelete.post.data

import com.miguelete.domain.Post
import com.miguelete.post.data.database.PostEntity
import com.miguelete.post.data.server.apimodels.PostApiModel

fun PostApiModel.toDomain() = Post(
    id,
    userId,
    title,
    body
)

fun Post.toEntity() = PostEntity(
    id,
    userId,
    title,
    body
)

fun PostEntity.toDomain() = Post(
    id,
    userId,
    title,
    body
)
