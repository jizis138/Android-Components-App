package ru.vsibi.androidcomponents_common.util

import android.util.Log
import kotlin.coroutines.cancellation.CancellationException

sealed class CallResult<out T> {

    data class Success<out T>(val data: T) : CallResult<T>()

    data class Error(val error: Throwable) : CallResult<Nothing>()
}

inline fun <T> CallResult<T>.onSuccess(action: (T) -> Unit): CallResult<T> = apply {
    if (this is CallResult.Success) {
        action(data)
    }
}

fun <T> CallResult<T>.getOrThrow(): T =
    when (this) {
        is CallResult.Success -> data
        is CallResult.Error -> throw error
    }

fun <T> CallResult<T>.getOrNull(): T? =
    when (this) {
        is CallResult.Success -> data
        is CallResult.Error -> null
    }

fun CallResult<*>.exceptionOrNull(): Throwable? =
    when (this) {
        is CallResult.Success -> null
        is CallResult.Error -> error
    }

fun <T> CallResult<T>.getOrDefault(default: T): T =
    when (this) {
        is CallResult.Success -> data
        is CallResult.Error -> default
    }

inline fun <T, R> CallResult<T>.map(mapper: (T) -> R): CallResult<R> =
    when (this) {
        is CallResult.Error -> this
        is CallResult.Success -> CallResult.Success(mapper(data))
    }

inline fun <T> callForResult(block: () -> T): CallResult<T> =
    try {
        CallResult.Success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (error: Throwable) {
        Log.e("CallForResult", "Error[${error::class}] message: ${error.message}")
        CallResult.Error(error)
    }