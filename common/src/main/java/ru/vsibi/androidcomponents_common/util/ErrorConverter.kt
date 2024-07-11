package ru.vsibi.androidcomponents_common.util

import ru.vsibi.androidcomponents_common.R


interface ErrorConverter {
    fun convert(throwable: Throwable): ErrorInfo
}

object DefaultErrorConverter : ErrorConverter {
    override fun convert(throwable: Throwable): ErrorInfo {
        val (title, description) = when (throwable) {
            else -> R.string.common_error_title to R.string.common_error_description
        }

        return ErrorInfo(
            title = PrintableText.StringResource(title),
            description = PrintableText.StringResource(description)
        )
    }
}