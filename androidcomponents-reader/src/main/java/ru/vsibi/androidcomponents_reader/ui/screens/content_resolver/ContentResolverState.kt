/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.content_resolver

import ru.vsibi.androidcomponents_common.util.LoadingState

data class ContentResolverState(
    val words: LoadingState<List<String>>
)