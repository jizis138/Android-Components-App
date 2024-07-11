/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.content_provider

import ru.vsibi.androidcomponents_common.util.LoadingState

data class ContentProviderState(
    val words: LoadingState<List<String>>
)