/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.content_provider

interface WordsStorage {

    suspend fun insert(word : String)

    fun getWords() : List<String>

}