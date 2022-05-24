package com.miguelete.post.data.server.apimodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostApiModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Parcelable
