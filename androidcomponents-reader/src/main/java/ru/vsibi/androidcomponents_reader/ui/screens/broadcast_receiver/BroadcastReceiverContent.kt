/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import org.koin.compose.koinInject

@Composable
fun BroadcastReceiverContent(modifier: Modifier = Modifier) {

    val vm = koinInject<BroadcastReceiverViewModel>()

    val viewState = vm.viewState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        vm.subscribeWords()
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (viewState.value.words.isEmpty()) {
            Text(
                modifier = Modifier.padding(24.dp),
                textAlign = TextAlign.Center,
                text = "Отправленные события из Writer отобразим здесь"
            )
        } else {
            LazyColumn {
                items(viewState.value.words) {
                    Text(text = it)
                }
            }
        }
    }

}