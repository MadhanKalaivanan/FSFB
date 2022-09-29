package com.fsfb.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()

    val isSuccess get() = this is Success<T>
    val isFailure get() = this is Failure

    fun onSuccess(action: (T) -> Unit) {
        when (this) {
            is Success -> action(data)
        }
    }

    fun onFailure(action: (Exception) -> Unit) {
        when (this) {
            is Failure -> action(exception)
        }
    }
}