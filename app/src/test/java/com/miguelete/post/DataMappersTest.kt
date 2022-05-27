package com.miguelete.post

import com.miguelete.post.data.toDomain
import com.miguelete.post.data.toEntity
import com.miguelete.testshared.post1
import com.miguelete.testshared.posts
import org.junit.Test

import org.junit.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataMappersTest {
    @Test
    fun `when mapping forward and back content is the same`() {
        assertEquals(post1(), post1().toEntity().toDomain())
    }

    @Test
    fun `when mapping list forward and back content is the same`() {
        assertEquals(posts(), posts().toEntity().toDomain())
    }
}
