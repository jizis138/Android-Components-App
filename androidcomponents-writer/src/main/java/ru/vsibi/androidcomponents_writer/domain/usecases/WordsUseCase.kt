/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.domain.usecases

import ru.vsibi.androidcomponents_writer.domain.repository.WordsRepository

class WordsUseCase(private val wordsRepository: WordsRepository){

    suspend fun addWord(word : String) = wordsRepository.addWord(word)

    suspend fun getWords() = wordsRepository.getWords()

}