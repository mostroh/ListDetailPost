package com.miguelete.post.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
