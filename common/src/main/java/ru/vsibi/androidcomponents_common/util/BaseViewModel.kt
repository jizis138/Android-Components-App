package ru.vsibi.androidcomponents_common.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S : Any> : ViewModel() {

    private val _viewState by lazy { MutableStateFlow(createInitialState()) }

    val viewState get() = _viewState.asStateFlow()

    protected abstract fun createInitialState(): S

    protected open val errorConverter: ErrorConverter = DefaultErrorConverter

    protected fun updateState(update: (S) -> S) {
        _viewState.update(update)
    }

}