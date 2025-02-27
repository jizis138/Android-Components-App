/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.content_provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "words.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "words"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_VALUE = "value"

        private const val TABLE_CREATE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_VALUE TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}