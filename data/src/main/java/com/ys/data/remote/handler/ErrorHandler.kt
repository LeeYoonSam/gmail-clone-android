package com.ys.data.remote.handler

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import okio.IOException
import retrofit2.HttpException

fun Throwable.toEither(): Either<Failure, Nothing> {
    return when (this) {
        is IOException -> Either.Left(Failure.NetworkError(this))
        is HttpException -> {
            val code = code()
            val message = message()
            Either.Left(Failure.ServerError(code, message))
        }
        else -> Either.Left(Failure.UnknownError(this))
    }
}