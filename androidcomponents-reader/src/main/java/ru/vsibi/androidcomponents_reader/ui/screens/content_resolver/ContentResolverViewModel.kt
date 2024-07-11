/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.content_resolver

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsibi.androidcomponents_common.util.BaseViewModel
import ru.vsibi.androidcomponents_common.util.CallResult
import ru.vsibi.androidcomponents_common.util.LoadingState
import ru.vsibi.androidcomponents_common.util.dataOrNull
import ru.vsibi.androidcomponents_reader.domain.usecases.WordsUseCase

class ContentResolverViewModel(
    private val wordsUseCase: WordsUseCase
) : BaseViewModel<ContentResolverState>() {

    override fun createInitialState(): ContentResolverState {
        return ContentResolverState(
            words = LoadingState.None
        )
    }

    fun getWords() {
        viewModelScope.launch {
            when (val result = wordsUseCase.getWords()) {
                is CallResult.Error -> {
                    onError(throwable = result.error)
                }

                is CallResult.Success -> {
                    updateState { state ->
                        state.copy(
                            words = LoadingState.Loaded(result.data)
                        )
                    }
                }
            }
        }
    }

    private fun onError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                words = LoadingState.Error(errorInfo = errorConverter.convert(throwable))
            )
        }
    }


}