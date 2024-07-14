/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.content_resolver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import org.koin.compose.koinInject
import ru.vsibi.androidcomponents_common.util.dataOrNull

@Composable
fun ContentResolverContent(modifier: Modifier = Modifier) {

    val vm = koinInject<ContentResolverViewModel>()

    val viewState = vm.viewState.collectAsState()

    val data by remember {
        derivedStateOf {
            viewState.value.words.dataOrNull ?: listOf()
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        vm.getWords()
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (data.isEmpty()) {
            Text(
                modifier = Modifier.padding(24.dp),
                textAlign = TextAlign.Center,
                text = "Отправленные данные из Writer отобразим здесь"
            )
        } else {
            LazyColumn {
                items(data) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Center,
                        text = it
                    )
                }
            }
        }
    }
}