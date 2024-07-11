/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.content_provider

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsibi.androidcomponents_writer.domain.usecases.WordsUseCase
import ru.vsibi.androidcomponents_common.util.CallResult
import ru.vsibi.androidcomponents_common.util.LoadingState
import ru.vsibi.androidcomponents_common.util.dataOrNull
import ru.vsibi.androidcomponents_common.util.BaseViewModel

class ContentProviderViewModel(
    private val wordsUseCase: WordsUseCase
) : BaseViewModel<ContentProviderState>() {

    init {
        getWords()
    }

    override fun createInitialState(): ContentProviderState {
        return ContentProviderState(
            words = LoadingState.None
        )
    }

    private fun getWords() {
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

    fun addWord(word: String) {
        viewModelScope.launch {
            when (val result = wordsUseCase.addWord(word)) {
                is CallResult.Error -> {
                    onError(throwable = result.error)
                }

                is CallResult.Success -> {
                    updateState { state ->
                        val oldData = state.words.dataOrNull ?: listOf()

                        val newData = oldData.plus(word)

                        state.copy(
                            words = LoadingState.Loaded(newData)
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