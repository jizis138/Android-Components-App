/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.domain.repository

import kotlinx.coroutines.flow.SharedFlow
import ru.vsibi.androidcomponents_common.util.CallResult

interface WordsRepository {

    fun getWords() : CallResult<List<String>>

}