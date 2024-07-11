/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.domain.usecases

import ru.vsibi.androidcomponents_reader.domain.repository.WordsRepository

class WordsUseCase(private val wordsRepository: WordsRepository) {

    fun getWords() = wordsRepository.getWords()

}