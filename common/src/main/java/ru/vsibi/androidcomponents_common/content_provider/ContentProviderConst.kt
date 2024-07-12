package ru.vsibi.androidcomponents_common.content_provider

import android.net.Uri

object ContentProviderConst {

    const val AUTHORITY = "ru.vsibi.androidcomponents_writer"
    const val BASE_PATH = "data"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$BASE_PATH")

}