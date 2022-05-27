package com.miguelete.post.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    fun getAll(): Flow<List<PostEntity>>

    @Query("SELECT * FROM PostEntity WHERE id = :id")
    fun findById(id: Int): Flow<PostEntity>

    @Query("SELECT COUNT(id) FROM PostEntity")
    suspend fun postCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(posts: List<PostEntity>)
}
