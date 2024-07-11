/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.data.repository_impl

import kotlinx.coroutines.flow.SharedFlow
import ru.vsibi.androidcomponents_common.util.CallResult
import ru.vsibi.androidcomponents_common.util.callForResult
import ru.vsibi.androidcomponents_reader.data.content_resolver.ReaderContentResolver
import ru.vsibi.androidcomponents_reader.domain.repository.WordsRepository

class WordsRepositoryImpl(private val readerContentResolver: ReaderContentResolver) : WordsRepository {

    override fun getWords(): CallResult<List<String>> = callForResult {
        readerContentResolver.read()
    }

}