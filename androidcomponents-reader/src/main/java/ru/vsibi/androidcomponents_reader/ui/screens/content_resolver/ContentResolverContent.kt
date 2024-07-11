/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.content_resolver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import org.koin.compose.koinInject
import ru.vsibi.androidcomponents_common.util.dataOrNull

@Composable
fun ContentResolverContent(modifier : Modifier = Modifier) {

    val vm = koinInject<ContentResolverViewModel>()

    val viewState = vm.viewState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        vm.getWords()
    }

    Box(modifier = modifier) {
        LazyColumn {
            items(viewState.value.words.dataOrNull ?: listOf()){
                Text(text = it)
            }
        }
    }
}