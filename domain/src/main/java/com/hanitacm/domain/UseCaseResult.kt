package com.hanitacm.domain

sealed class UseCaseResult<out T> {
    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Error<T>(val error: Exception) : UseCaseResult<Nothing>()
}
