package ru.vsibi.androidcomponents_common.util

import androidx.compose.runtime.Composable

sealed interface LoadingState<out Data> {

    data class Loaded<Data>(val data: Data) : LoadingState<Data>

    data class Error(val errorInfo: ErrorInfo) : LoadingState<Nothing>

    data object Loading : LoadingState<Nothing>

    data object None : LoadingState<Nothing>

}

val <Data> LoadingState<Data>.dataOrNull: Data?
    get() = (this as? LoadingState.Loaded)?.data

@Composable
fun <Data> LoadingState<Data>.RenderLoadingState(
    onLoaded: @Composable (Data) -> Unit = {},
    onLoading: @Composable () -> Unit = {},
    onError: @Composable (ErrorInfo) -> Unit = {}
) {
    when (this) {
        is LoadingState.Error -> onError(errorInfo)
        is LoadingState.Loaded -> onLoaded(data)
        LoadingState.Loading -> onLoading()
        LoadingState.None -> Unit
    }
}
