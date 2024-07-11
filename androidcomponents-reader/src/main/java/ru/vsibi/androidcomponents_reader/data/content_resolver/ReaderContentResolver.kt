/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.data.content_resolver

import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ReaderContentResolver(private val context: Context) {

    companion object {
        private const val AUTHORITY = "ru.vsibi.androidcomponents_writer"
        private const val BASE_PATH = "data"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$BASE_PATH")
    }

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