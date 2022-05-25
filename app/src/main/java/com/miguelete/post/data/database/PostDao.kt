package com.miguelete.post.data.database

import androidx.room.*

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    fun getAll(): List<PostEntity>

    @Query("SELECT * FROM PostEntity WHERE id = :id")
    fun findById(id: Int): PostEntity

    @Query("SELECT COUNT(id) FROM PostEntity")
    fun postCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(posts: List<PostEntity>)

    @Update
    fun updatePost(post: PostEntity)
}
