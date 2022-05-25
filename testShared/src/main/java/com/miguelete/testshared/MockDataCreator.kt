package com.miguelete.testshared

import com.miguelete.domain.Post

fun post1() = Post(
    1,
    1,
    "title1",
    "body1"
)

fun post2() = Post(
    2,
    1,
    "title2",
    "body2"
)

fun post3() = Post(
    3,
    2,
    "title3",
    "body3"
)

fun posts() = listOf(
    post1(), post2(), post3()
)