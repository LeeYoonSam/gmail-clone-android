package com.ys.core.functional

sealed class Either<out L, out R> {
    data class Left<L>(val value: L) : Either<L, Nothing>()
    data class Right<R>(val value: R) : Either<Nothing, R>()
}

inline fun <L, R, T> Either<L, R>.fold(
    left: (L) -> T,
    right: (R) -> T
): T = when (this) {
    is Either.Left -> left(value)
    is Either.Right -> right(value)
}