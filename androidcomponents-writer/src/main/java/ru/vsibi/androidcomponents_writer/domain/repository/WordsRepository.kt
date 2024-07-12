/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.domain.repository

import ru.vsibi.androidcomponents_common.util.CallResult

interface WordsRepository {

    suspend fun getWords() : CallResult<List<String>>

    suspend fun addWord(word : String) : CallResult<Unit>

    suspend fun sendWord(word : String) : CallResult<Unit>

}