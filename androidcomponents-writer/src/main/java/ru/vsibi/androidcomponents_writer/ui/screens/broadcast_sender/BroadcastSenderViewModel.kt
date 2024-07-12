/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.broadcast_sender

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vsibi.androidcomponents_common.util.BaseViewModel
import ru.vsibi.androidcomponents_writer.domain.usecases.WordsUseCase

class BroadcastSenderViewModel(private val wordsUseCase: WordsUseCase) : BaseViewModel<BroadcastSenderState>() {

    override fun createInitialState(): BroadcastSenderState {
        return BroadcastSenderState()
    }

    fun sendWord(word: String) {
        viewModelScope.launch {
            wordsUseCase.sendWord(word)
        }
    }

}