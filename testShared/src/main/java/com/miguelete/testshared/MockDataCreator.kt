package com.miguelete.testshared

import com.miguelete.domain.Post

fun post1() = Post(
    id = 1,
    userId = 1,
    title = "title1",
    body = "body1"
)

fun post2() = Post(
    id = 2,
    userId = 1,
    title = "title2",
    body = "body2"
)

fun post3() = Post(
    id = 3,
    userId = 2,
    title = "title3",
    body = "body3"
)

fun posts() = listOf(
    post1(), post2(), post3()
)