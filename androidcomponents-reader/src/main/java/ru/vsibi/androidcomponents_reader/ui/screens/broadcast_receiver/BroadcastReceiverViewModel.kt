/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsibi.androidcomponents_common.util.BaseViewModel
import ru.vsibi.androidcomponents_reader.data.broadcast_receiver.WordsObserver

class BroadcastReceiverViewModel(private val wordsObserver: WordsObserver) : BaseViewModel<BroadcastReceiverState>() {

    override fun createInitialState(): BroadcastReceiverState {
        return BroadcastReceiverState(words = listOf())
    }

    fun subscribeWords() {
        viewModelScope.launch {
            wordsObserver.subscribe().collect { word ->
                updateState { state ->
                    state.copy(
                        words = state.words.plus(word)
                    )
                }
            }
        }
    }
}