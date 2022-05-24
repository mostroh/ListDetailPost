package com.miguelete.post.data.server

import com.miguelete.post.data.server.apimodels.PostApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("post")
    suspend fun getPostList(): List<PostApiModel>

    @GET("post/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ): PostApiModel
}
