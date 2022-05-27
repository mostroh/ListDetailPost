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

fun List<PostApiModel>.toDomainModel(): List<Post> = map { it.toDomain() }

fun Post.toEntity(): PostEntity = PostEntity(
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

fun List<PostEntity>.toDomain() = map { it.toDomain() }

fun List<Post>.toEntity(): List<PostEntity> = map { it.toEntity() }