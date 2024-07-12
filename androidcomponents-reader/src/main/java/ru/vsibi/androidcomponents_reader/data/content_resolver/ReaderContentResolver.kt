/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.data.content_resolver

import android.content.Context
import android.database.Cursor
import ru.vsibi.androidcomponents_common.content_provider.ContentProviderConst.CONTENT_URI

class ReaderContentResolver(private val context: Context) {

    fun read(): List<String> {
        val cursor: Cursor? = context.contentResolver.query(CONTENT_URI, null, null, null, null)
        val cars = mutableListOf<String>()

        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow("name"))
                val value = it.getString(it.getColumnIndexOrThrow("value"))

                cars.add(value)
            }
        }
        return cars
    }
}