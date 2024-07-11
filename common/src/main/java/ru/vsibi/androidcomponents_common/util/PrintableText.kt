/**
 * Created by Dmitry Popov on 10.07.2024.
 */
package ru.vsibi.androidcomponents_common.util

sealed class PrintableText {

    class StringResource(val resId: Int) : PrintableText()

    class Raw(val text: String) : PrintableText()

}