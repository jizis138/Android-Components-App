/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.content_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import ru.vsibi.androidcomponents_common.content_provider.ContentProviderConst.AUTHORITY
import ru.vsibi.androidcomponents_common.content_provider.ContentProviderConst.BASE_PATH
import ru.vsibi.androidcomponents_common.content_provider.ContentProviderConst.CONTENT_URI

class WriterContentProvider : ContentProvider() {

    companion object {

        private const val DATA = 1
        private const val DATA_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, BASE_PATH, DATA)
            addURI(AUTHORITY, "$BASE_PATH/#", DATA_ID)
        }
    }

    private lateinit var database: SQLiteDatabase

    override fun onCreate(): Boolean {
        if (context == null) return false
        val helper = SQLiteDatabaseHelper(context!!)
        database = helper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        println("CARIS QUERY")
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = SQLiteDatabaseHelper.TABLE_NAME


        when (uriMatcher.match(uri)) {
            DATA -> {}
            DATA_ID -> queryBuilder.appendWhere("${SQLiteDatabaseHelper.COLUMN_ID}=${ContentUris.parseId(uri)}")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        val cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val id = database.insert(SQLiteDatabaseHelper.TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val rowsDeleted = when (uriMatcher.match(uri)) {
            DATA -> database.delete(SQLiteDatabaseHelper.TABLE_NAME, selection, selectionArgs)
            DATA_ID -> {
                val id = ContentUris.parseId(uri)
                database.delete(SQLiteDatabaseHelper.TABLE_NAME, "${SQLiteDatabaseHelper.COLUMN_ID}=?", arrayOf(id.toString()))
            }

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val rowsUpdated = when (uriMatcher.match(uri)) {
            DATA -> database.update(SQLiteDatabaseHelper.TABLE_NAME, values, selection, selectionArgs)
            DATA_ID -> {
                val id = ContentUris.parseId(uri)
                database.update(
                    SQLiteDatabaseHelper.TABLE_NAME,
                    values,
                    "${SQLiteDatabaseHelper.COLUMN_ID}=?",
                    arrayOf(id.toString())
                )
            }

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return rowsUpdated
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            DATA -> "vnd.android.cursor.dir/$AUTHORITY.$BASE_PATH"
            DATA_ID -> "vnd.android.cursor.item/$AUTHORITY.$BASE_PATH"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}
