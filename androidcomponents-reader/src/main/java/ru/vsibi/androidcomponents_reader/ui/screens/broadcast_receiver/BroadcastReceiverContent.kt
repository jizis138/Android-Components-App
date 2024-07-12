/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import org.koin.compose.koinInject

@Composable
fun BroadcastReceiverContent(modifier : Modifier = Modifier){

    val vm = koinInject<BroadcastReceiverViewModel>()

    val viewState = vm.viewState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        vm.subscribeWords()
    }

    Box(modifier = modifier) {
        LazyColumn {
            items(viewState.value.words){
                Text(text = it)
            }
        }
    }

}