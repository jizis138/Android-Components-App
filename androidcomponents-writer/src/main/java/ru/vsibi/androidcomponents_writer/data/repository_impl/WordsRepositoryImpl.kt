/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.repository_impl

import ru.vsibi.androidcomponents_common.util.CallResult
import ru.vsibi.androidcomponents_common.util.callForResult
import ru.vsibi.androidcomponents_writer.data.broadcast_sender.WordsBroadcastSender
import ru.vsibi.androidcomponents_writer.data.content_provider.WordsStorage
import ru.vsibi.androidcomponents_writer.domain.repository.WordsRepository

class WordsRepositoryImpl(
    private val wordsStorage: WordsStorage,
    private val wordsBroadcastSender: WordsBroadcastSender
) : WordsRepository {

    override suspend fun getWords(): CallResult<List<String>> = callForResult {
        wordsStorage.getWords()
    }

    override suspend fun addWord(word: String): CallResult<Unit> = callForResult {
        wordsStorage.insert(word)
    }

    override suspend fun sendWord(word: String): CallResult<Unit> = callForResult {
        wordsBroadcastSender.sendBroadcast("word", word)
    }

}