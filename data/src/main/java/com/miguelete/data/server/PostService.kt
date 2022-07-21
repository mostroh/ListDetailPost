package com.miguelete.data.server

import com.miguelete.data.server.apimodels.PostApiModel
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getPostList(): List<PostApiModel>
}
