package com.miguelete.post

import retrofit2.HttpException
import java.io.IOException
import com.miguelete.domain.Error

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}