/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.content_provider

import android.content.ContentValues
import ru.vsibi.androidcomponents_writer.data.content_provider.SQLiteDatabaseHelper.Companion.TABLE_NAME

class WordsStorageImpl(private val sqLiteDatabaseHelper: SQLiteDatabaseHelper) : WordsStorage {

    override suspend fun insert(word: String) {
        val values = ContentValues().apply {
            put(SQLiteDatabaseHelper.COLUMN_NAME, "word")
            put(SQLiteDatabaseHelper.COLUMN_VALUE, word)
        }

        sqLiteDatabaseHelper.writableDatabase.insert(TABLE_NAME, null, values)
    }

    override fun getWords(): List<String> {

        val cursor = sqLiteDatabaseHelper.readableDatabase.rawQuery("SELECT * FROM words", null)
        val words = mutableListOf<String>()
        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow("name"))
                val value = it.getString(it.getColumnIndexOrThrow("value"))

                words.add(value)
            }
        }
        return words
    }

}